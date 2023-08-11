package com.application.services;

import com.amazonaws.services.sqs.model.Message;
import com.application.constants.PatternConst;
import com.application.domain.DimensionsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class WorkerApplication {

    private static final Logger logger = LoggerFactory.getLogger("test");

    public static DimensionsModel CreateDimensions(String message) {

        Pattern pattern = Pattern.compile(PatternConst.REGEX.getStmt());

        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String timestamp = matcher.group(1);
            String logLevel = matcher.group(2);
            Integer cpuUsage = Integer.parseInt(matcher.group(3));
            Integer ramUsage = Integer.parseInt(matcher.group(4));
            String apiEndpoint = matcher.group(5);
            String status = matcher.group(6);
            String serviceName = matcher.group(7);
            String ipAddress = matcher.group(8);

            String outputMessage = String.format("\n TimeStamp : %s \n Label: %s \n CPU and Ram: %d - %d \n Endpoint: %s \n service: %s \n IP: %s ",timestamp,logLevel,cpuUsage,ramUsage,apiEndpoint,serviceName,ipAddress);
            logger.info(outputMessage);

            return new DimensionsModel(timestamp,logLevel,cpuUsage,ramUsage,apiEndpoint,status,serviceName,ipAddress);

        } else {
            logger.info("No match found.");
        }

        return null;
    }

    public static void InsertIntoDatabase(String message){

        DimensionsModel dimension = CreateDimensions(message);

        if(LogDataHandlerRDS.InsertIntoDatabase(dimension)){
            logger.info("Insertion in to RDS Successful");


            if(dimension.getStatus() == PatternConst.ERROR_LEVEL.getStmt()){
                LogHandlerSns.publishMessageToSNS("message","topic");
            }

        }

    }



    @Scheduled(fixedDelay = 20000)
    public static void pollDataFromSqs(){
        List<Message> messages = LogHandlerSqs.ReceiveMessageFromQueue();

        if(messages.isEmpty()){
            logger.info("sqs is empty");
           return;
        }

        for(Message i : messages){
            logger.info("PollingData From SQS"+ i.getBody());
            LogHandlerSqs.DeleteFromSQS(i);
            String temp = String.valueOf(i.getBody());
            InsertIntoDatabase(temp.substring(4, temp.length() - 2));
        }

    }


}
