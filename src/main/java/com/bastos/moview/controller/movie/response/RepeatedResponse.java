package com.bastos.moview.controller.movie.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepeatedResponse {

    @JsonProperty("message")
    private String message;
}