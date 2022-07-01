package com.bastos.moview.controller.user;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateModeratorResponse {

    private String username;
    private String profile;
    private String message;
}
