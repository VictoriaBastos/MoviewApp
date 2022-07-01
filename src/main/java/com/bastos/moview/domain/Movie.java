package com.bastos.moview.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @NotNull
    private long omdbMovieId;

    private double avgScore;

    @OneToMany(mappedBy = "movie")
    private List<Score> scores = new ArrayList<>();

    @OneToMany(mappedBy = "movieClient")
    private List<Review> reviews = new ArrayList<>();

    public Movie(String title) {
        this.title = title;
    }
}
