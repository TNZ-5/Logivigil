package com.application.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkDynamoSQSTest {

    @Test
    void pollDataFromDynamo() {
        LinkDynamoSQS.pollDataFromDynamo();
    }
}