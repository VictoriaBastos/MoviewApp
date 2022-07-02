package com.bastos.moview.controller.movie;

import com.bastos.moview.controller.movie.request.AnswerReviewRequest;
import com.bastos.moview.controller.movie.request.ReviewRequest;
import com.bastos.moview.controller.movie.request.ScoreRequest;
import com.bastos.moview.controller.movie.response.*;
import com.bastos.moview.repositories.MovieRepository;
import com.bastos.moview.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/filmes")
@AllArgsConstructor
public class MoviesController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;


    @GetMapping("/titulo")
    public ResponseEntity<OmdbMovieResponse> getMovieByTitle(@RequestParam(name = "title", required = false) String title) {
        if ((Objects.isNull(title) || title.isBlank())){
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Necessario filtrar busca por titulo");
        }
        OmdbMovieResponse movie = movieService.getMovieByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OmdbMovieResponse> getMovieById(@PathVariable(name = "id") String id) {
        if (Objects.isNull(id)){
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Necessario filtrar busca por id");
        }
        OmdbMovieResponse movie = movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
    @PreAuthorize("hasAnyRole('LEITOR')")

    @PostMapping("/avaliacao")
    public ResponseEntity<ScoreResponse> postScoreMovie(@Valid @RequestBody ScoreRequest scoreResquest){

        ScoreResponse scoreResponse = movieService.postScoreMovie(scoreResquest);
        return ResponseEntity.status(201).body(scoreResponse);
    }

    @PreAuthorize("hasAnyRole('BASICO')")

    @PostMapping("/comentario")
    public ResponseEntity<ReviewResponse> postReviewMovie(@Valid @RequestBody ReviewRequest reviewResquest){
        ReviewResponse reviewResponse = movieService.postReviewMovie(reviewResquest);
        return ResponseEntity.status(201).body(reviewResponse);
    }
    @PreAuthorize("hasAnyRole('BASICO')")

    @PostMapping("/comentario/resposta/{id}")
    public ResponseEntity<AnswerReviewResponse> postAnswerReviewMovie(@PathVariable int id,
                                                                      @Valid @RequestBody AnswerReviewRequest answerReviewRequest){
        AnswerReviewResponse answerReviewResponse = movieService.postAnswerReviewMovie(id, answerReviewRequest);
        return ResponseEntity.status(201).body(answerReviewResponse);
    }
    @PreAuthorize("hasAnyRole('AVANCADO')")

    @PatchMapping("/comentario/repetido/{id}")
    public ResponseEntity<RepeatedResponse> putRepeatedReview(@PathVariable int id,
                                                              @RequestParam(name = "comentario-repetido", required = true) boolean repeated) {

        RepeatedResponse repeatedResponse = movieService.putRepeatedReview(id, repeated);
        return ResponseEntity.status(201).body(repeatedResponse);
    }
    @PreAuthorize("hasAnyRole('AVANCADO')")

    @PatchMapping("/comentario/like/{id}")
    public ResponseEntity<ReactionResponse> putLikeComment( @PathVariable int id,
                                                            @RequestParam(name = "like", required = true) boolean like) {
        ReactionResponse likeResponse = movieService.likeReview(id, like);
        return ResponseEntity.status(201).body(likeResponse);
    }

    @PreAuthorize("hasAnyRole('MODERADOR')")
    @PatchMapping("/comentario/dislike/{id}")
    public ResponseEntity<ReactionResponse> putDislikeComment(@PathVariable int id,
                                                              @RequestParam(name = "dislike", required = true) boolean dislike){
        ReactionResponse dislikeResponse = movieService.dislikeReview(id, dislike);
        return ResponseEntity.status(201).body(dislikeResponse);
    }
}

