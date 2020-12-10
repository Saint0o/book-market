package ru.geekbrains.mini.market.geekbrainsminimarket.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.geekbrainsminimarket.dto.AuthorDto;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Author;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Book;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.MarketError;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Api("Set of endpoints for authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //GET http://localhost:9999/market/api/v1/authors/1
    @GetMapping("/{id}")
    @ApiOperation("Returns a specific author by their identifier. 404 if does not exist.")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        Author author = authorService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find author with id: " + id));
        return new AuthorDto(author);
    }

    //GET http://localhost:9999/market/api/v1/authors?name=Joanne+Rowling
    @GetMapping
    @ApiOperation("Returns author by name")
    public AuthorDto getAuthorByName(@RequestParam String name) {
        Author author = authorService.getByName(name).orElseThrow(() -> new ResourceNotFoundException("Unable to find author with name: " + name));
        return new AuthorDto(author);
    }

    //POST http://localhost:9999/market/api/v1/authors/{id}
    @PostMapping
    @ApiOperation("Creates a new author. If id != null, then throw bad request response")
    public ResponseEntity<?> createNewBook(@RequestBody Author author) {
        if (author.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authorService.save(author), HttpStatus.CREATED);
    }

}
