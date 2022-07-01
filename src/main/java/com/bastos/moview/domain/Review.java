package com.bastos.moview.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    private String username;

    private boolean repeated;

    private int like;

    private int dislike;

    @ManyToOne
    @JoinColumn(name = "movie_client_id")
    private Movie movieClient;

    @OneToMany(mappedBy = "review")
    private List<Reactions> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<Answers> answer = new ArrayList<>();


}
