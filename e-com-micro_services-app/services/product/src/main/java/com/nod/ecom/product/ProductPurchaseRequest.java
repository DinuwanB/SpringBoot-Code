package com.nod.ecom.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id is mandatory")
        Integer productId,
        @NotNull(message = "Quantity  is mandatory")
        Integer quantity
) {
}
