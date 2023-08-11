package com.application.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerApplicationTest {

    @Test
    void createDimensions() {
        String input = "2023-06-25 12:34:56 MyApp 80% CPU, 1024 MB RAM /api/data Success SomeService 192.168.1.100";
        WorkerApplication.InsertIntoDatabase(input);
    }

}