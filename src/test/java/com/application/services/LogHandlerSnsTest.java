package com.application.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogHandlerSnsTest {

    @Test
    void publishMessageToSNS() {
        LogHandlerSns.publishMessageToSNS("This is an alert","arn:aws:sns:us-west-2:000000000000:Alerts");
    }
}