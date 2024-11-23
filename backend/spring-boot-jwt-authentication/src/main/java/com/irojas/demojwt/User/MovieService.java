package com.irojas.demojwt.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<MovieResponse> getMovieById(Long id) {
        return movieRepository.findById(id).map(this::mapToResponse);
    }

    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .releaseDate(movieRequest.getReleaseDate())
                .genre(movieRequest.getGenre())
                .director(movieRequest.getDirector())
                .durationMinutes(movieRequest.getDurationMinutes())
                .imageUrl(movieRequest.getImageUrl())
                .build();

        return mapToResponse(movieRepository.save(movie));
    }

    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setGenre(movieRequest.getGenre());
            movie.setDirector(movieRequest.getDirector());
            movie.setDurationMinutes(movieRequest.getDurationMinutes());
            movie.setImageUrl(movieRequest.getImageUrl());
            return mapToResponse(movieRepository.save(movie));
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .durationMinutes(movie.getDurationMinutes())
                .createdAt(movie.getCreatedAt().toString())
                .build();
    }
}
