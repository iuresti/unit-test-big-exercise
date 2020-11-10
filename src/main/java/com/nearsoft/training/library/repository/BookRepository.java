package com.nearsoft.training.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nearsoft.training.library.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
