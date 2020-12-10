package ru.geekbrains.mini.market.geekbrainsminimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
