package com.application.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.application.constants.DynamoDBConst;
import com.application.constants.PatternConst;
import com.application.domain.DimensionsModel;
import com.application.domain.LogModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogDataHandlerDynamo {

    private static final Logger logger = LoggerFactory.getLogger("test");
    private List<LogModel> listOfLogs = new ArrayList<>();

    public static List<LogModel> MarshalPayload(String payload){

        List<String> tokens = tokenizePayload(payload);
        List<LogModel> listOfEntries =  packageLogs(tokens);

        logger.info("Log Models Created as Follows : ");

        for(LogModel i : listOfEntries){
            logger.info("Log Message" + i.getMessage());
        }

        if(listOfEntries.isEmpty())
            return null;

        return listOfEntries;
    }

    public static boolean InsertIntoTable(String payload){

        List<LogModel> logs = MarshalPayload(payload);

        DynamoDBMapper dynamoDBMapper = AWSHandler.getMapperInstanceDynamo();

        if(dynamoDBMapper == null)
            return false;

        for(LogModel i : logs){
            logger.info("Inserting : " + i.getMessage());
            dynamoDBMapper.save(i);
        }

        return true;
    }

    private static List<String> tokenizePayload(String payload) {

        List<String> tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile(PatternConst.REGEX.getStmt(), Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(payload);

        while (matcher.find()) {
            String parsedToken = matcher.group();
            tokens.add(parsedToken);
        }

        return tokens;
    }

    private static List<LogModel> packageLogs(List<String> tokens) {

        List<LogModel> logModelList = new ArrayList<>();

        for( String i : tokens){
            logModelList.add(new LogModel(null,i, Integer.parseInt(DynamoDBConst.TTL.getStmt())));
        }

        return logModelList;
    }


    public static ScanResult getLatestImage() {

        AmazonDynamoDB client = AWSHandler.getClientInstanceDynamo();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("LogStream");

        ScanResult result = client.scan(scanRequest);

        if(result.getItems().isEmpty()){
            return null;
        }

        return result;
    }

    public static void deleteDynamoDBItem(String key, String keyVal) {

        AmazonDynamoDB ddb = AWSHandler.getClientInstanceDynamo();

        HashMap<String,AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put(key, new AttributeValue(keyVal));

        DeleteItemRequest deleteReq = new DeleteItemRequest(DynamoDBConst.Constants.TABLE_NAME,keyToGet);

        try {
            ddb.deleteItem(deleteReq);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }


}
