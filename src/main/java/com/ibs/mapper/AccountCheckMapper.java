package com.ibs.mapper;

import com.ibs.dto.response.AccountCheckResponse;
import com.ibs.dto.response.Debt;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountCheckMapper {

    AccountCheckResponse toResponse(String account, boolean vipClient, String inn, List<Debt> debt);

}