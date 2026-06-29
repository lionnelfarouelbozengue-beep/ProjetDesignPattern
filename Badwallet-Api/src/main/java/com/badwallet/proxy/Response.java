package com.badwallet.proxy;

public class Response {
    private boolean success;
    private String transactionReference;
    private String message;

    public Response() {}

    public Response(boolean success, String transactionReference, String message) {
        this.success = success;
        this.transactionReference = transactionReference;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}