package org.example.grpcclient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private BigDecimal cost;
    private String bookContent;
}
