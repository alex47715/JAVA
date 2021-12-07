package com.example.aviasales.DTO;

import lombok.Getter;

@Getter
public class OrderDTO {

    private Integer count;
    private Integer accountId;
    private Integer productId;

    public OrderDTO(Integer quantity, Integer accountId, Integer productId) {
        this.count = quantity;
        this.accountId = accountId;
        this.productId = productId;
    }
}
