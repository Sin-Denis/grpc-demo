package org.example.grpcclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookApiService bookApiService;

    @GetMapping
    public BookDto getBook(Long id) {
        return bookApiService.getBook(id);
    }
}
