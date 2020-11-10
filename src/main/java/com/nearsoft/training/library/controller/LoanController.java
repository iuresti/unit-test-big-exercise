package com.nearsoft.training.library.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.service.LoanService;
import com.nearsoft.training.library.service.UserService;

@RestController
@RequestMapping("/api")
public class LoanController {

    private LoanService loanService;

    private UserService userService;

    public LoanController(LoanService loanService, UserService userService) {
        this.loanService = loanService;
        this.userService = userService;
    }

    @PostMapping("/lend")
    public void lendRequest(@RequestBody String[] isbnList) {
        loanService.lendBooks(isbnList);
    }

    @PostMapping("/return")
    public void returnRequest(@RequestBody String[] isbnList) {
        loanService.returnBooks(isbnList);
    }

    @GetMapping("/user/{curp}/books")
    public ResponseEntity<Set<BooksByUser>> returnRequest(@PathVariable String curp) {
        return new ResponseEntity<>(userService.getBorrowedBooks(curp), HttpStatus.OK);
    }

}
