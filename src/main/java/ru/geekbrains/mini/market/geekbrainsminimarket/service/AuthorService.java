package ru.geekbrains.mini.market.geekbrainsminimarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Author;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Book;
import ru.geekbrains.mini.market.geekbrainsminimarket.repositories.AuthorRepository;


import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getOneById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
