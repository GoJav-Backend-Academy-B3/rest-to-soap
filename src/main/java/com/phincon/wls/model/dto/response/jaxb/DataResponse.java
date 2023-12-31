package com.phincon.wls.model.dto.response.jaxb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataResponse<T> {
    private Integer statusCode;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object metadata;

    public static <T> ResponseEntity<DataResponse<T>> ok(T data) {
        DataResponse<T> body = new DataResponse<>();
        body.data = data;
        body.statusCode = HttpStatus.OK.value();
        body.message = HttpStatus.OK.name();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<DataResponse<T>> ok(T data, Object metadata) {
        DataResponse<T> body = new DataResponse<>();
        body.data = data;
        body.metadata = metadata;
        body.statusCode = HttpStatus.OK.value();
        body.message = HttpStatus.OK.name();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<DataResponse<T>> created(T data) {
        DataResponse<T> body = new DataResponse<>();
        body.data = data;
        body.statusCode = HttpStatus.CREATED.value();
        body.message = HttpStatus.CREATED.name();
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static <T> ResponseEntity<DataResponse<T>> accepted(T data) {
        DataResponse<T> body = new DataResponse<>();
        body.data = data;
        body.statusCode = HttpStatus.ACCEPTED.value();
        body.message = HttpStatus.ACCEPTED.name();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }
}