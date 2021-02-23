package org.example.bookservice;

import org.example.bookservice.Book;
import org.example.bookservice.BookType;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    ConcurrentMap<Long, Book> bookStorage = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        bookStorage.put(1L, new Book()
                .setId(1L)
                .setName("Effective java")
                .setAuthor("Joshua Bloch")
                .setCost(BigDecimal.valueOf(4232))
                .setType(BookType.EDUCATION));
        bookStorage.put(2L, new Book()
                .setId(2L)
                .setName("Жизнь без правил")
                .setAuthor("Кавана Джон")
                .setCost(BigDecimal.valueOf(649))
                .setType(BookType.SPORT));
        bookStorage.put(3L, new Book()
                .setId(3L)
                .setName("ЛеБрон, INC")
                .setAuthor("Уиндхорст Брайан")
                .setCost(BigDecimal.valueOf(559))
                .setType(BookType.SPORT));
        bookStorage.put(4L, new Book()
                .setId(4L)
                .setName("Беспощадная истина")
                .setAuthor("Тайсон Майк")
                .setCost(BigDecimal.valueOf(509))
                .setType(BookType.BIOGRAPHY));
        bookStorage.put(5L, new Book()
                .setId(5L)
                .setName("Исчезнувшая")
                .setAuthor("Флинн Гиллиан")
                .setCost(BigDecimal.valueOf(188))
                .setType(BookType.DETECTIVE));
    }

    public Book findById(Long id) {
        return bookStorage.get(id);
    }

    public List<Book> findByCostRange(BigDecimal from, BigDecimal to) {
        return bookStorage.values().stream()
                .filter(book -> book.getCost().compareTo(from) >= 0 &&
                        book.getCost().compareTo(to) <= 0 )
                .collect(Collectors.toList());
    }
}
