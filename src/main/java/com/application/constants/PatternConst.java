package com.application.constants;

public enum PatternConst {

    ERROR_LEVEL("Critical"),
    REGEX("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) ([A-Za-z]+) (\\d+)% CPU, (\\d+) MB RAM (/api\\/[A-Za-z]+) (Success|Error|Warning|Critical) ([A-Za-z]+) (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})$");

    private final String stmt;

    PatternConst(String stmt) {
        this.stmt = stmt;
    }

    public String getStmt(){
        return stmt;
    }
}
