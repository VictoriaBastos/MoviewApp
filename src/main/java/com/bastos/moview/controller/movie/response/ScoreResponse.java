package com.bastos.moview.controller.movie.response;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {
    private String title;
    private String imdbID;
    private String message;
}

