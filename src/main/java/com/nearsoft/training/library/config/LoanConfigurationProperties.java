package com.nearsoft.training.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfigurationProperties {

    private int maxBooksPerUser;

    public int getMaxBooksPerUser() {
        return maxBooksPerUser;
    }

    public void setMaxBooksPerUser(int maxBooksPerUser) {
        this.maxBooksPerUser = maxBooksPerUser;
    }
}
