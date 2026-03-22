package com.subham.user_auth_svc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String errMessage;

    @JsonProperty("data")
    private Object data;
}
