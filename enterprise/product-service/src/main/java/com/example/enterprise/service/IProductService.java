package com.example.enterprise.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.enterprise.dto.ProductCreateRequest;
import com.example.enterprise.dto.ProductResponse;
import com.example.enterprise.dto.ProductUpdateRequest;

public interface IProductService {
    ProductResponse create(ProductCreateRequest request);
    ProductResponse getById(Long id);
    Page<ProductResponse> findAll(Pageable pageable);
    ProductResponse update(Long id, ProductUpdateRequest request);
    void delete(Long id);
}

	
