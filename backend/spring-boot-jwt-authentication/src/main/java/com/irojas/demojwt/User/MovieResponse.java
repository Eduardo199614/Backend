package com.irojas.demojwt.User;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private Integer durationMinutes;
    private String createdAt;

}
