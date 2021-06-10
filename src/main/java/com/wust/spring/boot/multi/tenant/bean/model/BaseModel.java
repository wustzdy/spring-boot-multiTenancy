package com.wust.spring.boot.multi.tenant.bean.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.Map;

@Data
public class BaseModel {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long accountId;
    @JsonUnwrapped
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String, Object> extras;
}
