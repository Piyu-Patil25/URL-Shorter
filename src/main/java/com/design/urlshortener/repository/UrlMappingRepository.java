package com.design.urlshortener.repository;

import com.design.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    // Find by short URL
    Optional<UrlMapping> findByShortUrl(String shortUrl);
}
