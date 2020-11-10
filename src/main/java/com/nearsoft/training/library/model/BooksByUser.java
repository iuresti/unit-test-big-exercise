package com.nearsoft.training.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity(name = "books_by_user")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
        "isbn", "curp"
}))
public class BooksByUser implements Serializable {

    private static final long serialVersionUID = 688441185690791172L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String isbn;

    private String curp;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksByUser that = (BooksByUser) o;
        return Objects.equals(isbn, that.isbn) &&
                Objects.equals(curp, that.curp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, curp);
    }
}
