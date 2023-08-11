package com.application.constants;
public enum DynamoDBConst {


    TTL("2");
    private String constant;

    DynamoDBConst(String constant){
        this.constant = constant;
    }

    public String getStmt(){
        return constant;
    }

    public static class Constants {
        public static final String TABLE_NAME = "LogStream";
    }


}
