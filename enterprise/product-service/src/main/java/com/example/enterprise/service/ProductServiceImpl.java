package com.example.enterprise.service;
 
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.enterprise.dto.ProductCreateRequest;
import com.example.enterprise.dto.ProductResponse;
import com.example.enterprise.dto.ProductUpdateRequest;
import com.example.enterprise.exceptions.ConflictException;
import com.example.enterprise.exceptions.ResourceNotFoundException;
import com.example.enterprise.mapper.ProductMapper;
import com.example.enterprise.model.Product;
import com.example.enterprise.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(
        ProductRepository repository,
        ProductMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {
        if (repository.existsBySku(request.sku())) {
            throw new ConflictException("SKU already exists");
        }

        Product product = mapper.toEntity(request);
        return mapper.toResponse(repository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        return mapper.toResponse(findProduct(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public ProductResponse update(
        Long id,
        ProductUpdateRequest request
    ) {
        Product product = findProduct(id);

        if (!product.getVersion().equals(request.version())) {
            throw new OptimisticLockingFailureException(
                "Product was modified by another request"
            );
        }

        mapper.update(request, product);
        return mapper.toResponse(repository.save(product));
    }

    @Override
    public void delete(Long id) {
        Product product = findProduct(id);
        repository.delete(product);
    }

    private Product findProduct(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Product", id)
            );
    }
}

