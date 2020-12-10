package ru.geekbrains.mini.market.geekbrainsminimarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Author;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class BookDto {
    private Long id;
    private String title;
    private List<AuthorDto> products;

    public BookDto(Author c) {
        this.id = c.getId();
        this.title = c.getTitle();
        this.products = c.getBooks().stream().map(AuthorDto::new).collect(Collectors.toList());
    }
}
