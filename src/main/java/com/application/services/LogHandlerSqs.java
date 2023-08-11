package com.application.services;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.application.constants.SqsConst;

import java.util.List;

public class LogHandlerSqs {


    public static void InsertIntoSqs(String message){
        AmazonSQS client = AWSHandler.getClientInstanceSQS();

        String queueUrl = client.getQueueUrl(SqsConst.Constants.QUEUE_NAME).getQueueUrl();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message);
        client.sendMessage(send_msg_request);

    }

    public static void DeleteFromSQS(Message m){

        AmazonSQS sqs = AWSHandler.getClientInstanceSQS();
        String queueUrl = sqs.getQueueUrl(SqsConst.Constants.QUEUE_NAME).getQueueUrl();
        sqs.deleteMessage(queueUrl, m.getReceiptHandle());
    }

    public static List<Message> ReceiveMessageFromQueue(){
        AmazonSQS sqs = AWSHandler.getClientInstanceSQS();
        String queueUrl = sqs.getQueueUrl(SqsConst.Constants.QUEUE_NAME).getQueueUrl();

        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        return messages;
    }


}
