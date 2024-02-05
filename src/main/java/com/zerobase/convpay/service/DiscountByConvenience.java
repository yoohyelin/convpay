package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public class DiscountByConvenience implements DiscountInterface{
    @Override
    public Integer getDiscountAmount(PayRequest payRequest) {
        switch(payRequest.getConveienceType()) {

            case G25 -> {
                return payRequest.getPayAmount() * 8 /10;
            }
            case GU -> {
                return payRequest.getPayAmount() * 9 /10;
            }
            case SEVEN -> {
                return payRequest.getPayAmount();
            }
        }
        return payRequest.getPayAmount();
    }
}
