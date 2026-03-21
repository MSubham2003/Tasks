package com.subham.studentRecord.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subham.studentRecord.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String errMessage;

    @JsonProperty("data")
    private Object data;
}
