package com.api.themoviedb.controller;

import com.api.themoviedb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {
    private final ApiService apiService;

    @Autowired

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }
    @GetMapping("/movies/now_playing")
    public String nowPlaying() {
        try {
            System.out.println(apiService.getNowPlayingMovies());
            return apiService.getNowPlayingMovies();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/movies/upcoming")
    public String upcoming() {
        try {
            return apiService.getUpcomingMovies();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
