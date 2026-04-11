package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.Report;

@RepositoryRestResource(path = "reports")
public interface ReportRepository extends CrudRepository<Report, Long> {
}
