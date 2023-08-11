package com.application.domain;

public class DimensionsModel {
    private String timestamp;
    private String level;
    private Integer cpu;
    private Integer ram;
    private String apiEndpoint;
    private String status;
    private String serviceName;
    private String ip;


    public DimensionsModel(String timestamp, String level, Integer cpu, Integer ram, String apiEndpoint, String status, String serviceName, String ip) {
        this.timestamp = timestamp;
        this.level = level;
        this.cpu = cpu;
        this.ram = ram;
        this.apiEndpoint = apiEndpoint;
        this.status = status;
        this.serviceName = serviceName;
        this.ip = ip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

