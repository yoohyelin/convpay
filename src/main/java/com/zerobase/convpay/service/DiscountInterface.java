package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public interface DiscountInterface {
    Integer getDiscountAmount(PayRequest payRequest);

}
