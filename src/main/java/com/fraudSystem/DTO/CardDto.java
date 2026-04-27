package com.fraudSystem.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CardDto {

    @NotBlank(message = "Card Number is Required")
    private String cardNumber;

    @NotBlank(message = "Field is required")
    private String expiry;

    @NotNull(message = "Amount is Required")
    @Min(value = 1,message = "Amount must be greater than 0")
    private Double balance;
    private Long userId;
}
