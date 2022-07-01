package com.bastos.moview.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Answers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("answer")
    private String answerTxt;

    @ManyToOne
    @JoinColumn(name = "review_client_id")
    private Review review;


    public Answers(String username, String answerTxt) {
        this.username = username;
        this.answerTxt = answerTxt;
    }
}

