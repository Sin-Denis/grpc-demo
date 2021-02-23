package org.example.bookservice;

import lombok.RequiredArgsConstructor;
import org.example.bookservice.Book;
import org.example.bookservice.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book getById(Long id) {
        return repository.findById(id);
    }

    public List<Book> getByCostRange(BigDecimal from, BigDecimal to) {
        return repository.findByCostRange(from, to);
    }
}
