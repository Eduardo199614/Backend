package com.irojas.demojwt.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private Integer durationMinutes;
    private String imageUrl;
}
