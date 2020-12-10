package ru.geekbrains.mini.market.geekbrainsminimarket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Book;

@Data
@NoArgsConstructor
@ApiModel(description = "Product dto in the application.")
public class AuthorDto {
    @ApiModelProperty(notes = "Unique identifier of the product. No two products can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Title of the product.", example = "Bread", required = true, position = 1)
    private String title;

    @ApiModelProperty(notes = "Price of the product.", example = "100", required = true, position = 2)
    private int price;

    @ApiModelProperty(notes = "Author title of the product.", example = "Food", required = true, position = 3)
    private String categoryTitle;

    public AuthorDto(Book p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getAuthor().getTitle();
    }
}
