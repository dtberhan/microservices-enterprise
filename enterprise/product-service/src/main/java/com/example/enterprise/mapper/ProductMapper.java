package com.example.enterprise.mapper;

import com.example.enterprise.dto.ProductCreateRequest;
import com.example.enterprise.dto.ProductResponse;
import com.example.enterprise.dto.ProductUpdateRequest;
import com.example.enterprise.model.Product;

import org.mapstruct.*;

	@Mapper(componentModel = "spring")
	public interface ProductMapper {

	    ProductResponse toResponse(Product product);

	    Product toEntity(ProductCreateRequest request);

	    @Mapping(target = "id", ignore = true)
	    @Mapping(target = "sku", ignore = true)
	    @Mapping(target = "version", source = "version")
	    @Mapping(target = "createdAt", ignore = true)
	    @Mapping(target = "updatedAt", ignore = true)
	    void update(ProductUpdateRequest request, @MappingTarget Product product);
	}


