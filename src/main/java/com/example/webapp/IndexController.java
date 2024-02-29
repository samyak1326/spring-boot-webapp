package com.example.webapp;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class IndexController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getIndexPage() throws IOException {

        Resource resource = new ClassPathResource("static/index.html");

        InputStream inputStream = resource.getInputStream();
        String htmlContent = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(htmlContent);
    }

    @GetMapping(value = "/*", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getErrorPage() throws IOException {
        Resource resource = new ClassPathResource("static/404.html");

        InputStream inputStream = resource.getInputStream();
        String htmlContent = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(htmlContent);
    }
}
