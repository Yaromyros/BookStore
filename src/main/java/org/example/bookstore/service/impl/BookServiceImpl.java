package org.example.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.CreateBookRequestDto;
import org.example.bookstore.exception.EntityNotFoundException;
import org.example.bookstore.mapper.BookMapper;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.service.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toEntity(createBookRequestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id
                        + " not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto createBookRequestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id "
                        + id + " not found"));

        Book updatedBook = updateBook(createBookRequestDto, existingBook);
        Book savedBook = bookRepository.save(updatedBook);

        return bookMapper.toDto(savedBook);
    }

    private Book updateBook(CreateBookRequestDto createBookRequestDto, Book existingBook) {
        Book updatedBook = bookMapper.toEntity(createBookRequestDto);
        updatedBook.setId(existingBook.getId());
        return updatedBook;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
