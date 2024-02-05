package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService { // 편결이
    // 구현체에 의존
    // 구현체를 결제서비스가 직접 선탤하여 의존
    // 해결방법: 의존 관계 관리 클래스를 따로 분리
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        ); // List.forEach 노션 참고
        this.discountInterface = discountInterface;
    }


    // private final DiscountInterface discountInterface = new DiscountConvenience();
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountedAmount = discountInterface.getDiscountAmount(payRequest);
        PaymentResult payment = paymentInterface.payment(discountedAmount);
        // fail fast

        // Method()


        // Exception case1
        // Exception case2
        // Exception case3

        // Success Case(Only one)

        if(payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL,0);
        }
        // Success Case
        // 성공케이스는 가장 마지막에
        return new PayResponse(PayResult.SUCCESS,discountedAmount);
    }

    public class ConveniencePayService_noSpring { // 편결이
        // 구현체에 의존
        private final MoneyAdapter moneyAdapter = new MoneyAdapter();
        private final CardAdapter cardAdapter = new CardAdapter();
        // 구현체를 결제서비스가 직접 선탤하여 의존
        // 해결방법: 의존 관계 관리 클래스를 따로 분리
        private final DiscountInterface discountInterface = new DiscountByPayMethod();
        // private final DiscountInterface discountInterface = new DiscountConvenience();
        public PayResponse pay(PayRequest payRequest) {
            PaymentInterface paymentInterface;

            if(payRequest.getPayMethodType() == PayMethodType.CARD){
                paymentInterface = cardAdapter;
            }else {
                paymentInterface = moneyAdapter;
            }

            Integer discountedAmount = discountInterface.getDiscountAmount(payRequest);
            PaymentResult payment = paymentInterface.payment(discountedAmount);
            // fail fast

            // Method()


            // Exception case1
            // Exception case2
            // Exception case3

            // Success Case(Only one)

            if(payment == PaymentResult.PAYMENT_FAIL) {
                return new PayResponse(PayResult.FAIL,0);
            }
            // Success Case
            // 성공케이스는 가장 마지막에
            return new PayResponse(PayResult.SUCCESS,discountedAmount);
        }
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelpayment(
                payCancelRequest.getPayCancelAmount());

        if(cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        // Success Case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,
                payCancelRequest.getPayCancelAmount());
    }
}
