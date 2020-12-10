package ru.geekbrains.mini.market.geekbrainsminimarket.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Author;

@NoArgsConstructor
@Data
public class AuthorDto {

    @ApiModelProperty(notes = "Unique identifier of the product. No two products can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Author's name.", example = "Uncle Bob", required = true, position = 1)
    private String name;

    @ApiModelProperty(notes = "Books count.", example = "3", required = true, position = 2)
    private Integer booksCount;

    public AuthorDto(Author a) {
        this.id = a.getId();
        this.name = a.getName();
        this.booksCount = a.getBooks().size();
    }
}
