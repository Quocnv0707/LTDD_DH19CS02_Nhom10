package com.ltdd.ltdd_dh19cs02_nhom10.model;

import java.util.List;

public class LoaiSPModel {
    private boolean success;
    private String message;
    private List<LoaiSP> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoaiSP> getResult() {
        return result;
    }

    public void setResult(List<LoaiSP> result) {
        this.result = result;
    }
}
