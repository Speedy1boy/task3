package ru.vladislav.javanaumen.controller;

import org.springframework.web.bind.annotation.*;
import ru.vladislav.javanaumen.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Long createReport() {
        return reportService.createReport();
    }

    @GetMapping("/{id}")
    public String getReport(@PathVariable Long id) {
        return reportService.getReport(id);
    }
}
