package org.example.bookservice;

import org.example.grpc.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface BookToBookResponseMapper {

    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "authorBytes", ignore = true)
    @Mapping(target = "typeValue", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    BookResponse map(Book book);

    List<BookResponse> map(List<Book> books);
}
