package org.example.grpcclient;

import org.example.grpc.BookResponse;
import org.example.grpcclient.MapStructConfig;
import org.example.grpcclient.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface BookResponseToBookDtoMapper {

    @Mapping(target = "bookContent", ignore = true)
    BookDto map(@MappingTarget BookDto book, BookResponse bookResponse);
}
