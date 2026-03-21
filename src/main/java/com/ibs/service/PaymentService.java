package com.ibs.service;

import com.ibs.dto.request.PaymentRequest;
import com.ibs.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse confirmPayment(PaymentRequest request, String bankCode);
}