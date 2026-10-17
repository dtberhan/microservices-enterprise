package com.example.enterprise.dto;

import java.math.BigDecimal;
import java.time.Instant;
 

public record ProductResponse(
		Long id,
		String sku,
		String name,
		BigDecimal price,
		Integer quantity, 
		Long version,
		Instant createdAt, 
		Instant updatedAt
		)
{}
 

