package com.example.enterprise.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductCreateRequest(
		    @NotBlank @Size(max = 64) String sku,
		    @NotBlank @Size(max = 200) String name,
		    @NotNull @DecimalMin("0.00") BigDecimal price,
		    @NotNull @PositiveOrZero Integer quantity
		) {}


