package com.design.urlshortener.service;

import com.design.urlshortener.model.UrlMapping;
import com.design.urlshortener.repository.UrlMappingRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;

    // Constructor-based Dependency Injection (Best Practice)
    public UrlShortenerService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    // Generate short URL
    public String shortenUrl(String originalUrl) {
        String shortUrl = RandomStringUtils.randomAlphanumeric(6); // 6-character short URL
        UrlMapping urlMapping = new UrlMapping(originalUrl, shortUrl); // Using constructor directly
        urlMappingRepository.save(urlMapping);

        return shortUrl;
    }


    // Retrieve original URL
    public Optional<String> getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl)
                .map(UrlMapping::getOriginalUrl);
    }
}
