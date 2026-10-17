package com.example.enterprise.controller;


import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise.dto.ProductCreateRequest;
import com.example.enterprise.dto.ProductResponse;
import com.example.enterprise.dto.ProductUpdateRequest;
import com.example.enterprise.service.IProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final IProductService service;
	private final String baseUrl = "/api/v1/product";
	 
    
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPEproducts.write')")
    public ResponseEntity<ProductResponse> create(
        @Valid @RequestBody ProductCreateRequest request
    ) {
        ProductResponse response = service.create(request);
        return ResponseEntity
            .created(URI.create(baseUrl + response.id()))
            .body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPEproducts.read')")
    public ProductResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPEproducts.read')")
    public Page<ProductResponse> findAll(
        @PageableDefault(size = 20, sort = "id")
        Pageable pageable
    ) {
        return service.findAll(pageable);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPEproducts.write')")
    public ProductResponse update(
        @PathVariable Long id,
        @Valid @RequestBody ProductUpdateRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPEproducts.write')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
