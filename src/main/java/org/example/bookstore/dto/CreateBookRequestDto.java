package org.example.bookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequestDto {
    @NotNull(message = "Title cannot be null")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(max = 255, message = "Author must not exceed 255 characters")
    private String author;

    private String isbn;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters.")
    private String description;

    private String coverImage;
}
