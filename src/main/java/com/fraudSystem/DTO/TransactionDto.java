package com.fraudSystem.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {

    @NotNull(message = "Required Field")
    private Long cardId;

    @NotNull(message = "Amount is Required")
    @Min(value = 1,message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank
    private String location;
}
