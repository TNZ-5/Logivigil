package com.application.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHandlerSns {

    private static final Logger logger = LoggerFactory.getLogger("test");


    public static void publishMessageToSNS(String message, String topicArn) {

        AmazonSNS snsClient = AWSHandler.getClientInstanceSNS();

        try {
            PublishRequest request = new PublishRequest(topicArn,message);
            PublishResult result = snsClient.publish(request);

            logger.info(result.getMessageId() + " Message sent");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
