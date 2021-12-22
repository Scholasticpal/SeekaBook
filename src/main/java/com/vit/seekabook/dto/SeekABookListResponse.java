package com.vit.seekabook.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class SeekABookListResponse<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String WARNING = "WARNING";

    private final String status;
    private final String message;
    private final List<T> data;

    public SeekABookListResponse() {
        this(SUCCESS, SUCCESS, null);
    }

    public SeekABookListResponse(List<T> data) {
        this(SUCCESS, SUCCESS, data);
    }

    public SeekABookListResponse(String status, String message, List<T> data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            log.error("Error while parsing response", ex);
        }
        return "";
    }
}
