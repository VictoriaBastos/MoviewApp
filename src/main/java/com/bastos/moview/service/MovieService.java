package com.bastos.moview.service;

import com.bastos.moview.controller.movie.request.AnswerReviewRequest;
import com.bastos.moview.controller.movie.request.ReviewRequest;
import com.bastos.moview.controller.movie.request.ScoreRequest;
import com.bastos.moview.controller.movie.response.*;
import com.bastos.moview.domain.Answers;
import com.bastos.moview.domain.Review;
import com.bastos.moview.domain.Score;
import com.bastos.moview.domain.User;
import com.bastos.moview.enums.ProfileEnum;
import com.bastos.moview.repositories.MovieRepository;
import com.bastos.moview.repositories.ReviewRepository;
import com.bastos.moview.repositories.ScoreRepository;
import com.bastos.moview.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OmdbClientService omdbClientService;

    public OmdbMovieResponse getMovieByTitle(String title) {
        return omdbClientService.searchMovieByTitle(title);
    }

    public OmdbMovieResponse getMovieById(String id) {
        return omdbClientService.searchMovieById(id);
    }

    public ScoreResponse postScoreMovie(ScoreRequest scoreRequest) {

        Optional<User> user = userRepository.findByEmail(scoreRequest.getEmail());

        getMovieById(scoreRequest.getMovieId());

        if (user.isPresent()) {
            scoreRepository.save(Score.builder()
                    .movie(scoreRequest.getTitle())
                    .userClient(user.get())
                    .build());

            addPointsToUser(user.get());
            upgradeProfile(user.get());

            return new ScoreResponse(scoreRequest.getTitle(),
                    scoreRequest.getMovieId(),
                    "Nota atribuída com sucesso");
        }
        throw new RuntimeException("Nao foi possível atribuir a nota");
    }

    public ReviewResponse postReviewMovie(ReviewRequest reviewResquest) {

        Optional<User> user = userRepository.findByEmail(reviewResquest.getEmail());

        if (user.isPresent()) {
            reviewRepository.save(Review.builder()
                    .text(reviewResquest.getReviewText())
                    .username(user.get().getName())
                    .build());

            user.get().setPoints(Integer.sum((user.get().getPoints()),1));
            userRepository.save(user.get());

            return new ReviewResponse(reviewResquest.getTitle(), reviewResquest.getMovieId(), "Comentário adicionado com sucesso");
        }
        throw new RuntimeException("Não foi possível adicionar comentário");
    }

    public RepeatedResponse putRepeatedReview(int id, boolean repeated) {

        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            review.get().setRepeated(repeated);
            reviewRepository.save(review.get());

            return new RepeatedResponse("Comentário marcado como repetido");
        }
        throw new RuntimeException("Nao foi possível localizar o comentário");
    }

    public ReactionResponse dislikeReview(int id, boolean like) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            review.get().setDislike(review.get().getDislike());
            reviewRepository.save(review.get());

            return new ReactionResponse("Dislike adicionado com sucesso");
        }
        throw new RuntimeException("Não foi possível realizar a operação dislike");
    }

    public ReactionResponse likeReview(int id, boolean like) {

        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            review.get().setDislike(review.get().getDislike());
            reviewRepository.save(review.get());

            return new ReactionResponse("Like adicionado com sucesso");
        }
        throw new RuntimeException("Não foi possível realizar a operação like");
    }

    public AnswerReviewResponse postAnswerReviewMovie(int id, AnswerReviewRequest answerReviewRequest) {
        Optional<Review> review = reviewRepository.findById(id);
        Optional<User> user = userRepository.findByEmail(answerReviewRequest.getEmail());

        if (review.isPresent() && user.isPresent()) {
            review.get().setAnswer(List.of(new Answers(answerReviewRequest.getEmail(), answerReviewRequest.getAnswerText())));
            reviewRepository.save(review.get());

            addPointsToUser(user.get());
            upgradeProfile(user.get());

            return new AnswerReviewResponse("Comentario adicionado com sucesso");
        }
        throw new RuntimeException("Nao foi possivel adicionar o comentario");
    }

    private void addPointsToUser(User user){
        user.setPoints(Integer.sum((user.getPoints()),1));
    }

    private void upgradeProfile(User user){
        if (user.getPoints() >= 1000) {
            user.getProfile().setProfileName(ProfileEnum.ROLE_MODERADOR);
        }
        else if (user.getPoints() >= 100) {
            user.getProfile().setProfileName(ProfileEnum.ROLE_AVANCADO);
        }
        else if (user.getPoints() >= 20) {
            user.getProfile().setProfileName(ProfileEnum.ROLE_BASICO);
        }
        else {
            user.getProfile().setProfileName(ProfileEnum.ROLE_LEITOR);
        }
    }

}