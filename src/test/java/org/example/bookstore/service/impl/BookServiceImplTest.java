package org.example.bookstore.service.impl;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.CreateBookRequestDto;
import org.example.bookstore.exception.EntityNotFoundException;
import org.example.bookstore.mapper.BookMapper;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveAndReturnBookDto() {
        CreateBookRequestDto createDto = new CreateBookRequestDto();
        Book book = new Book();
        Book savedBook = new Book();
        BookDto expectedDto = new BookDto();

        when(bookMapper.toEntity(createDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(savedBook);
        when(bookMapper.toDto(savedBook)).thenReturn(expectedDto);

        BookDto result = bookService.save(createDto);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(bookRepository).save(book);
        verify(bookMapper).toEntity(createDto);
        verify(bookMapper).toDto(savedBook);
    }

    @Test
    void getById_ShouldReturnBookDto_WhenBookExists() {
        Long bookId = 1L;
        Book book = new Book();
        BookDto expectedDto = new BookDto();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(expectedDto);

        BookDto result = bookService.getById(bookId);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(bookRepository).findById(bookId);
        verify(bookMapper).toDto(book);
    }

    @Test
    void getById_ShouldThrowException_WhenBookNotFound() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.getById(bookId));
        verify(bookRepository).findById(bookId);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        Long bookId = 1L;

        bookService.deleteById(bookId);

        verify(bookRepository).deleteById(bookId);
    }
}
