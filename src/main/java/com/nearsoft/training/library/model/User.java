package com.nearsoft.training.library.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -8460691821621684257L;

    @Id
    private String curp;

    private String name;

    @Column(name = "validity_date")
    private String validityDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return curp.equals(user.curp) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curp, name);
    }
}
