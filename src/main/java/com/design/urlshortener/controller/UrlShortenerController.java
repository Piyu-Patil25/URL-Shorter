package com.design.urlshortener.controller;

import com.design.urlshortener.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")  // Ensure API prefix is correct
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        String shortUrl = urlShortenerService.shortenUrl(originalUrl);

        Map<String, String> response = new HashMap<>();
        response.put("shortUrl", shortUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortUrl) {
        return urlShortenerService.getOriginalUrl(shortUrl)
                .map(url -> ResponseEntity.status(302).header("Location", url).build())
                .orElse(ResponseEntity.notFound().build());
    }
}
