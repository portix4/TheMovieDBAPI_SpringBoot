package com.api.themoviedb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


@Service
public class ApiService {

    private final HttpClient httpClient;
    private static final String API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkN2ZmMjUwNGViMGI1YTQ3ZmRhNDZkNDk5NjljYzQyYSIsInN1YiI6IjY1NGNiMGEzNWE1ZWQwMDEwMDNmZWQ0NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WadaaSHFSItyR1k3SgL0jM93Y6huklnGPsvCyyyGfLE";
    private static String response;
    @Autowired
    public ApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getNowPlayingMovies() throws Exception {
        return sendRequest("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1");
    }

    public String getUpcomingMovies() throws Exception {
        response = sendRequest("https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1");
        return response;
    }

    private String sendRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
