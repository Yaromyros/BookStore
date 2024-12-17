package org.example.bookstore.service;

import java.util.List;
import org.example.bookstore.model.dto.BookDto;
import org.example.bookstore.model.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto getById(Long id);
}
