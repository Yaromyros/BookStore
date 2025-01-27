package org.example.bookstore.repository.book;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.BookSearchParametersDto;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.SpecificationBuilder;
import org.example.bookstore.repository.book.spec.AuthorSpecificationProvider;
import org.example.bookstore.repository.book.spec.IsbnSpecificationProvider;
import org.example.bookstore.repository.book.spec.TitleSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book,
        BookSearchParametersDto> {
    private final AuthorSpecificationProvider authorSpecificationProvider;
    private final IsbnSpecificationProvider isbnSpecificationProvider;
    private final TitleSpecificationProvider titleSpecificationProvider;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametersDto) {
        Specification<Book> spec = Specification.where(null);

        if (searchParametersDto.title() != null) {
            spec = spec.and(titleSpecificationProvider
                    .getSpecification(searchParametersDto.title()));
        }

        if (searchParametersDto.author() != null) {
            spec = spec.and(authorSpecificationProvider
                    .getSpecification(searchParametersDto.author()));
        }

        if (searchParametersDto.isbn() != null) {
            spec = spec.and(isbnSpecificationProvider
                    .getSpecification(searchParametersDto.isbn()));
        }

        return spec;
    }
}
