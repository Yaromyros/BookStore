package org.example.bookstore.repository.book.spec;

import org.example.bookstore.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider {
    private static final String AUTHOR_FIELD_NAME = "author";

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(AUTHOR_FIELD_NAME), param);
    }
}
