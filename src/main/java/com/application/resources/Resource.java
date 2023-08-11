package com.application.resources;

import com.amazonaws.Response;
import com.application.services.LogDataHandlerDynamo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Resource {

    @PostMapping("/logs")
    public ResponseEntity<String> setLogs(@RequestBody String payload) {

       if( LogDataHandlerDynamo.InsertIntoTable(payload)){
           return ResponseEntity.ok().body("Insertion Successful ! Log will be Ingested shortly");
       }

       return ResponseEntity.status(400).body("Insertion Failed !");

    }

    @GetMapping("/logs")
    public ResponseEntity<String> getLogs(){

        return ResponseEntity.ok().body("THis is a sample response");
    }

}
