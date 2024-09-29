package org.example.bookstore;

import java.math.BigDecimal;
import org.example.bookstore.model.Book;
import org.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Megan");
            book.setAuthor("Artur Defiko");
            book.setIsbn("978-3-16-148410-0");
            book.setPrice(new BigDecimal("29.99"));
            book.setDescription("This is a good book.");
            book.setCoverImage("cover_image_url.jpg");

            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
