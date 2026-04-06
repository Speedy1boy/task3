package ru.vladislav.javanaumen.service;

public interface ReportService {

    Long createReport();

    String getReport(Long id);

    void generateReportAsync(Long id);
}
