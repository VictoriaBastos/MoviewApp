package com.bastos.moview.integrations.omdb.impl;

import com.bastos.moview.controller.movie.response.OmdbMovieResponse;
import com.bastos.moview.integrations.omdb.client.OmdbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OmdbClientService {

    @Value("${apiKey}")
    private String apiKey;

    private final OmdbClient omdbClient;

    public OmdbClientService(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    public OmdbMovieResponse searchMovieByTitle(String title) {
        return omdbClient.getMovie(apiKey, title, null);
    }

    public OmdbMovieResponse searchMovieById(String id) {
        return omdbClient.getMovie(apiKey, null, id);
    }
}
