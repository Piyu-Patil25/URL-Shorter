package com.design.urlshortener.controller;

import com.design.urlshortener.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    // Constructor-based Injection
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    // Endpoint to shorten a URL
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String originalUrl) {
        String shortUrl = urlShortenerService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    // Endpoint to retrieve the original URL
    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        Optional<String> originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        return originalUrl.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
