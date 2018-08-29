package com.streaming.repositories;

import com.streaming.domains.Video;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {
}
