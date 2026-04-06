package ru.vladislav.javanaumen.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue()
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Lob
    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
