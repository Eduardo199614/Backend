package com.irojas.demojwt.Demo;
import com.irojas.demojwt.User.MovieRequest;
import com.irojas.demojwt.User.MovieResponse;
import com.irojas.demojwt.User.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")

public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> {
                     // Asignamos el valor a la respuesta
                    return ResponseEntity.ok(movie);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.createMovie(movieRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(id, movieRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
