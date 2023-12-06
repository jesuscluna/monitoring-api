package com.prevalentware.monitoringapp.errors;

public class ExceptionDetail {
    private int statusCode;
    private String timestamp;
    private String message;
    private String description;

    public ExceptionDetail() {}

    public ExceptionDetail(int statusCode, String timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() { return statusCode;	}

    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
