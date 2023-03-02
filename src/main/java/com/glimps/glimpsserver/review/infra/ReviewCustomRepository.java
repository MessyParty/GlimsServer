package com.glimps.glimpsserver.review.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

import com.glimps.glimpsserver.review.domain.Review;

public interface ReviewCustomRepository {
	Optional<Review> findByUuid(@Param("id") UUID id);
	List<Review> findTop10ByOrderByCreatedAtDesc();
}