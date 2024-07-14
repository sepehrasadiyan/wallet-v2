package me.sepehrasadiyan.wallet_v2.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@ToString
@Data
public class ErrorMessageResponse {

    private String status;

    private String description;

    private Integer statusCode;

    private Timestamp timestamp;

}