package com.application.services;



import com.application.domain.LogModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class LogDataHandlerDynamoTest {

    private static final Logger logger = LoggerFactory.getLogger("test");

    @Test
    void marshalPayload() {
        String input = "2023-06-25 12:34:56 MyApp 80% CPU, 1024 MB RAM /api/data Success SomeService 192.168.1.100\n"
                + "2023-06-25 12:35:00 MyApp 75% CPU, 512 MB RAM /api/user Error InvalidUser 192.168.1.200\n"
                + "2023-06-25 12:36:30 MyApp 90% CPU, 2048 MB RAM /api/info Success InfoService 192.168.1.50";

       LogDataHandlerDynamo.InsertIntoTable(input);

    }


    @Test
    void getLatestImage() {
        LogDataHandlerDynamo.getLatestImage();
    }
}