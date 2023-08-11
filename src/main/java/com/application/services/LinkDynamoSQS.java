package com.application.services;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class LinkDynamoSQS {

    private static Logger logger = LoggerFactory.getLogger("test");

    @Scheduled(fixedDelay = 100000)
    public static void pollDataFromDynamo(){

        ScanResult image = LogDataHandlerDynamo.getLatestImage();

        if(image == null){
            logger.info("Nothing inside the table");
            return;
        }

        for(Map<String, AttributeValue> items: image.getItems()){
            for(Map.Entry<String,AttributeValue> i : items.entrySet()){
                if(i.getKey() == "message"){
                    String temp = String.valueOf(i.getValue());
                    logger.info("Polling DataFrom Dynamo returns"+ temp.substring(4,temp.length()-2));
                    LogHandlerSqs.InsertIntoSqs(temp);
                }
            }
        }


        for(Map<String, AttributeValue> items: image.getItems()){
            for(Map.Entry<String,AttributeValue> i : items.entrySet()){
                if(i.getKey() == "id"){
                    String temp = String.valueOf(i.getValue());
                    LogDataHandlerDynamo.deleteDynamoDBItem("id",temp.substring(4,temp.length()-2));
                }
            }
        }

    }


}
