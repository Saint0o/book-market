package ru.geekbrains.mini.market.geekbrainsminimarket.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.geekbrainsminimarket.dto.AuthorDto;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Book;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.MarketError;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Api("Set of endpoints for products")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation("Returns all products")
    public List<Book> getAllProducts() {
        return bookService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific product by their identifier. 404 if does not exist.")
    public AuthorDto getProductById(@ApiParam("Id of the book to be obtained. Cannot be empty.") @PathVariable Long id) {
        Book p = bookService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
        return new AuthorDto(p);
    }

    @PostMapping
    @ApiOperation("Creates a new product. If id != null, then throw bad request response")
    public ResponseEntity<?> createNewProduct(@RequestBody Book p) {
        if (p.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.save(p), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Modify product")
    public ResponseEntity<?> modifyProduct(@RequestBody Book p) {
        if (p.getId() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be not null for new entity"), HttpStatus.BAD_REQUEST);
        }
        if (!bookService.existsById(p.getId())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product with id: " + p.getId() + " doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete product")
    public void deleteById(@ApiParam("Id of the product") @PathVariable Long id) {
        bookService.deleteById(id);
    }
}
