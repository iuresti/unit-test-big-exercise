package com.nearsoft.training.library.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nearsoft.training.library.config.CardReaderConfigurationProperties;
import com.nearsoft.training.library.exception.CardReaderFailureException;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.service.CardReaderService;

@Service
public class CardReaderServiceImpl implements CardReaderService {

    private static final Logger logger = LoggerFactory.getLogger(CardReaderServiceImpl.class);

    private CardReaderConfigurationProperties cardReaderConfigurationProperties;

    public CardReaderServiceImpl(CardReaderConfigurationProperties cardReaderConfigurationProperties) {
        this.cardReaderConfigurationProperties = cardReaderConfigurationProperties;
    }

    @Override
    public User readUser() {

        try (Socket socket = new Socket(cardReaderConfigurationProperties.getHost(), cardReaderConfigurationProperties.getPort())) {

            return getUser(socket.getOutputStream(), socket.getInputStream());

        } catch (IOException e) {
            logger.error("Can't connect to card reader device", e);
            throw new CardReaderFailureException(e);
        }
    }

    User getUser(OutputStream outputStream, InputStream inputStream) throws IOException {
        try(PrintWriter out = new PrintWriter(outputStream, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            out.print("AX0");

            String response = in.readLine();

            String[] data = response.split("\\|");

            User user = new User();
            user.setName(data[0]);
            user.setCurp(data[1]);
            user.setValidityDate(data[2]);

            return user;
        }
    }


}
