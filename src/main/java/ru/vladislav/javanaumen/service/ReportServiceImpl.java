package ru.vladislav.javanaumen.service;

import org.springframework.stereotype.Service;
import ru.vladislav.javanaumen.entity.Movie;
import ru.vladislav.javanaumen.entity.Report;
import ru.vladislav.javanaumen.entity.Status;
import ru.vladislav.javanaumen.repository.MovieRepository;
import ru.vladislav.javanaumen.repository.ReportRepository;
import ru.vladislav.javanaumen.repository.UserRepository;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReportServiceImpl(
            ReportRepository reportRepository,
            UserRepository userRepository,
            MovieRepository movieRepository
    ) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Long createReport() {
        var report = new Report();
        report.setStatus(Status.Created);
        reportRepository.save(report);
        generateReportAsync(report.getId());
        return report.getId();
    }

    @Override
    public String getReport(Long id) {
        var report = reportRepository.findById(id).orElseThrow();
        return switch (report.getStatus()) {
            case Created -> "Отчёт еще формируется";
            case Error -> "Ошибка при формировании отчёта";
            case Completed -> report.getContent();
        };
    }

    @Override
    public void generateReportAsync(Long id) {
        CompletableFuture.runAsync(() -> {
            var report = reportRepository.findById(id).orElseThrow();
            try {
                var startTime = System.currentTimeMillis();
                var usersCount = new long[1];
                var usersTime = new long[1];
                var moviesTime = new long[1];

                var movies = new ArrayList<Movie>();

                var usersThread = new Thread(() -> {
                    var start = System.currentTimeMillis();
                    usersCount[0] = userRepository.count();
                    usersTime[0] = System.currentTimeMillis() - start;
                });
                var moviesThread = new Thread(() -> {
                    var start = System.currentTimeMillis();
                    movieRepository.findAll().forEach(movies::add);
                    moviesTime[0] = System.currentTimeMillis() - start;
                });

                usersThread.start();
                moviesThread.start();
                usersThread.join();
                moviesThread.join();

                var elapsed = System.currentTimeMillis() - startTime;

                var moviesTable = movies.stream()
                        .map(m -> "<tr>" +
                                "<td>" + m.getId() + "</td>" +
                                "<td>" + m.getTitle() + "</td>" +
                                "<td>" + m.getGenre() + "</td>" +
                                "<td>" + m.getDescription() + "</td>" +
                                "<td>" + m.getDuration() + "</td>" +
                                "<td>" + m.getAgeRating() + "</td>" +
                                "</tr>")
                        .reduce("", (a, b) -> a + b);

                var html = """
                        <html>
                        <body>
                        <h1>Report</h1>
                        <p>Users count: %d</p>
                        <p>Users calculation time: %d ms</p>
                        <p>Movies calculation time: %d ms</p>
                        <p>Total report time: %d ms</p>
                        <h2>Movies:</h2>
                        <table border="1">
                        <tr>
                        <th>ID</th><th>Title</th><th>Genre</th><th>Description</th><th>Duration</th><th>Age rating</th>
                        </tr>
                        %s
                        </table>
                        </body>
                        </html>
                        """.formatted(
                        usersCount[0],
                        usersTime[0],
                        moviesTime[0],
                        elapsed,
                        moviesTable
                );
                report.setContent(html);
                report.setStatus(Status.Completed);

            } catch (Exception e) {
                report.setStatus(Status.Error);
            }

            reportRepository.save(report);
        });
    }
}