package org.example.bookstore.mapper;

import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.CreateBookRequestDto;
import org.example.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(CreateBookRequestDto createBookRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CreateBookRequestDto dto, @MappingTarget Book entity);
}
