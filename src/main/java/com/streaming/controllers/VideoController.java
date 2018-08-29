package com.streaming.controllers;

import com.streaming.domains.Video;
import com.streaming.repositories.VideoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostMapping("/list")
    public List<Video> listVideos() {
        List<Video> list = new ArrayList<>();
       videoRepository.findAll().forEach(list::add);
       return list;
    }
}