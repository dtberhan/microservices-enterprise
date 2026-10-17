package com.example.enterprise.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise.dto.ProductCreateRequest;
import com.example.enterprise.dto.ProductResponse;
import com.example.enterprise.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductCon {
	
 private final IProductService service;
 private String baseUrl = "/api/v1/product";
 
 @PostMapping
 @PreAuthorize("hasAuthority('SCOPEProducts.write')")
 public ResponseEntity<ProductResponse> create(ProductCreateRequest request){
	 ProductResponse response = service.create(request);
	 return ResponseEntity.created(URI.create(baseUrl + response.id()))
			 .body(response);
 }
 
 @GetMapping
 @PreAuthorize("hasAuthority('SCOPEProducs.read')")
 
 public Page<ProductResponse> findAll(@PageableDefault(size=20, sort="id") Pageable pageable){
	 return service.findAll(pageable); 
 }
 	
 
 
}
