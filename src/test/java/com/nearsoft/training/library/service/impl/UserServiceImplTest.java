package com.nearsoft.training.library.service.impl;


import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.repository.BooksByUserRepository;
import com.nearsoft.training.library.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class UserServiceImplTest {

    @Test
    public void whenGetBorrowedBooks_thenBooksFromRepositoryAreReturned(){
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



}
