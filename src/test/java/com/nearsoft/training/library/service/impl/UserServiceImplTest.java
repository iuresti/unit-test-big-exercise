package com.nearsoft.training.library.service.impl;


import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BooksByUserRepository;
import com.nearsoft.training.library.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {

    @Test
    public void whenGetBorrowedBooks_thenBooksFromRepositoryAreReturned() {
        // Given:
        UserRepository userRepository = null;
        BooksByUserRepository otherRepository = Mockito.mock(BooksByUserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, otherRepository);
        String curp = "ABC";
        Set<BooksByUser> booksByUser = new HashSet<>();

        Mockito.when(otherRepository.findByCurp(curp)).thenReturn(booksByUser);

        // When:
        Set<BooksByUser> receivedBooksByUser = userService.getBorrowedBooks(curp);

        // Then:
        assertTrue(booksByUser == receivedBooksByUser);

        Mockito.verify(otherRepository).findByCurp(curp);
        Mockito.verifyNoMoreInteractions(otherRepository);
    }


    @Test
    public void givenAnExistentUserWithAllBooksBorrowed_whenRegisterLoan_thenUserNotSavedLoanNotSaved() {
        // Given:
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BooksByUserRepository booksByUserRepository = Mockito.mock(BooksByUserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, booksByUserRepository);
        User user = new User();
        String[] isbnList = {"ABC", "DEF"};
        String curp = UUID.randomUUID().toString();

        user.setCurp(curp);

        Mockito.when(userRepository.findById(curp)).thenReturn(Optional.of(user));
        Mockito.when(booksByUserRepository.findByIsbnAndCurp("ABC", curp)).thenReturn(Optional.empty());
        Mockito.when(booksByUserRepository.findByIsbnAndCurp("DEF", curp)).thenReturn(Optional.empty());


        // When:
        userService.registerLoan(user, isbnList);

        // Then:
        Mockito.verify(userRepository).findById(curp);
        Mockito.verify(booksByUserRepository).findByIsbnAndCurp("ABC", curp);
        Mockito.verify(booksByUserRepository).findByIsbnAndCurp("DEF", curp);
        Mockito.verify(booksByUserRepository, Mockito.times(2)).save(any(BooksByUser.class));
        Mockito.verifyNoMoreInteractions(userRepository, booksByUserRepository);

    }

    @Test
    public void registerLoan2() {
        // Given:

        // When:

        // Then:
    }

}
