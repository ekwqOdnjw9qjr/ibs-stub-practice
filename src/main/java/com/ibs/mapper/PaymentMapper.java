package com.ibs.mapper;

import com.ibs.dto.request.PaymentRequest;
import com.ibs.dto.response.Contact;
import com.ibs.dto.response.PaymentResponse;
import com.ibs.entity.TransactionLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "request.transactionId", target = "transactionId")
    @Mapping(source = "bankBik", target = "bankBik")
    @Mapping(source = "request.needProcessing", target = "status")
    @Mapping(source = "contacts", target = "contact")
    PaymentResponse toResponse(PaymentRequest request, String bankBik, List<Contact> contacts);

    @Mapping(source = "request.transactionId", target = "transactionId")
    @Mapping(source = "bankCode", target = "bankCode")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "responseTime", target = "responseTime")
    TransactionLog toTransactionLog(PaymentRequest request, String bankCode, String status,
                                    LocalDateTime createdAt, LocalDateTime responseTime);

    Contact toContact(String name, List<String> telecom);
}
