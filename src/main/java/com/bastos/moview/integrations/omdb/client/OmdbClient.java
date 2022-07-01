package com.bastos.moview.integrations.omdb.client;

import com.bastos.moview.controller.movie.response.OmdbMovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "omdbClient", url = "${omdb.url}")
public interface OmdbClient {

    @GetMapping
    OmdbMovieResponse getMovie(@RequestParam("apikey") String apiKey,
                               @RequestParam(name = "t", required = false) String title,
                               @RequestParam(name = "i", required = false) String id);
}




