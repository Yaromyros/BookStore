package org.example.bookstore.repository.book.spec;

import org.example.bookstore.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider {
    private final String TITLE_FIELD_NAME = "title";

    public Specification<Book> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TITLE_FIELD_NAME), param);
    }
}
