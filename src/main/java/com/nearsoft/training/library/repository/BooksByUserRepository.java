package com.nearsoft.training.library.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nearsoft.training.library.model.BooksByUser;

public interface BooksByUserRepository extends JpaRepository<BooksByUser, Long> {

    Optional<BooksByUser> findByIsbnAndCurp(String isbn, String curp);

    void deleteByCurpAndIsbnIn(String curp, String[] isbnList);

    Set<BooksByUser> findByCurp(String curp);
}
