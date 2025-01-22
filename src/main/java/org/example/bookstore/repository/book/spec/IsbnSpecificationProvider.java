package org.example.bookstore.repository.book.spec;

import org.example.bookstore.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider {
    private static final String ISBN_FIELD_NAME = "isbn";

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(ISBN_FIELD_NAME), param);
    }
}
