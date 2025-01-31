package org.example.bookstore.service;

import java.util.List;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.BookSearchParametersDto;
import org.example.bookstore.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto params);
}
