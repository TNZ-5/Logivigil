package com.application.services;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.application.constants.AwsConst;
import com.amazonaws.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class AWSHandler {


    public static AmazonDynamoDB getClientInstanceDynamo(){
        return AmazonDynamoDBClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        AwsConst.Constants.ENDPOINT,AwsConst.Constants.REGION
                ))
                .build();
    }

    public static DynamoDBMapper getMapperInstanceDynamo(){

        try {
            AmazonDynamoDB dynamoDBClient = getClientInstanceDynamo();
            DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
            return dynamoDBMapper;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static AmazonSQS getClientInstanceSQS(){
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        AwsConst.Constants.ENDPOINT,AwsConst.Constants.REGION))
                .build();
    }

    public static AmazonSNS getClientInstanceSNS(){
            return AmazonSNSClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                            AwsConst.Constants.ENDPOINT,AwsConst.Constants.REGION))
                    .build();
    }




}
