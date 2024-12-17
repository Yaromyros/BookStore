package org.example.bookstore.mapper;

import org.example.bookstore.model.dto.BookDto;
import org.example.bookstore.model.dto.CreateBookRequestDto;
import org.example.bookstore.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(CreateBookRequestDto createBookRequestDto);
}
