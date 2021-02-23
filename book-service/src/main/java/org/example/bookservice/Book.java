package org.example.bookservice;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class Book {
    private Long id;
    private String name;
    private String author;
    private BigDecimal cost;
    private BookType type;
}
