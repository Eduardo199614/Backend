package com.irojas.demojwt.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "movies")
@Data // Genera getters y setters automáticamente
@NoArgsConstructor // Genera el constructor vacío
@AllArgsConstructor // Genera el constructor con todos los atributos
@Builder // Permite usar el patrón builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private Integer durationMinutes;

    private String imageUrl; // URL de la imagen

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


}
