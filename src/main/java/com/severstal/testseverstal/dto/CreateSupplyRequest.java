package com.severstal.testseverstal.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateSupplyRequest {
    @NotNull(message = "Date is required")
    private Date date;

    @NotEmpty(message = "At least one productId required")
    private List<Integer> productIds;

    public CreateSupplyRequest(Date date, List<Integer> integers) {
        this.date = date;
        productIds = integers;
    }
}
