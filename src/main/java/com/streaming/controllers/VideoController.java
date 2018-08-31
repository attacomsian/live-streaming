package com.streaming.controllers;

import com.streaming.domains.User;
import com.streaming.domains.Video;
import com.streaming.repositories.UserRepository;
import com.streaming.repositories.VideoRepository;
import com.streaming.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/videos")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    private VideoRepository videoRepository;
    private UserRepository userRepository;
    private FileStorageService fileStorageService;

    public VideoController(VideoRepository videoRepository,
                           UserRepository userRepository,
                           FileStorageService fileStorageService) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/list")
    public List<Video> listVideos() {
        return videoRepository.findByUserUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("/upload")
    public Video uploadVideo(@RequestParam("file") MultipartFile file, String title) {
        //store video file on the server
        String fileName = fileStorageService.storeFile(file);

        //load user details
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        //create a new video object
        Video video = new Video(user, title, file.getSize(), fileName);
        videoRepository.save(video);

        //return video object
        return video;
    }

    @GetMapping("/get/{id:.+}")
    public Video getVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.isPresent() ? video.get() : null;
    }

    @GetMapping("/download/{id:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) {
        Optional<Video> video = videoRepository.findById(id);
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(video.get().getUrl());

        // try to find file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // fallback content type
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}