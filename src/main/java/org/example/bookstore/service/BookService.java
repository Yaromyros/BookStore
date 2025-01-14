package org.example.bookstore.service;

import java.util.List;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);

    void deleteById(Long id);
}
