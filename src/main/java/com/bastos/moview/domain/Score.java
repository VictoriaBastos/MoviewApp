package com.bastos.moview.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieTitle;

    @ManyToOne
    @JoinColumn(name = "user_client_id")
    private User userClient;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

