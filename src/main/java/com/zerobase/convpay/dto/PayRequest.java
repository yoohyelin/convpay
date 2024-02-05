package com.zerobase.convpay.dto;

import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;

public class PayRequest {
    // 결제 수단
    PayMethodType payMethodType;
    // 편의점 종류
    ConvenienceType conveienceType;
    // 결제 금액
    Integer payAmount;

    public PayRequest(PayMethodType payMethodType,ConvenienceType conveienceType, Integer payAmount) {
        this.payMethodType = payMethodType;
        this.conveienceType = conveienceType;
        this.payAmount = payAmount;
    }

    public PayMethodType getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(PayMethodType payMethodType) {
        this.payMethodType = payMethodType;
    }

    public ConvenienceType getConveienceType() {
        return conveienceType;
    }

    public void setConveienceType(ConvenienceType conveienceType) {
        this.conveienceType = conveienceType;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }
}
