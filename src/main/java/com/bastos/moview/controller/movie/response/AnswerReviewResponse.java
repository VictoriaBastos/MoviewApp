package com.bastos.moview.controller.movie.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerReviewResponse {

    @JsonProperty("message")
    private String message;
}

