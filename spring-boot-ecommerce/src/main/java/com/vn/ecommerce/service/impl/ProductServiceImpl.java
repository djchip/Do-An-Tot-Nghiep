package com.vn.ecommerce.service.impl;

import com.vn.ecommerce.entities.Product;
import com.vn.ecommerce.repository.ProductRepository;
import com.vn.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product retrieve(Integer id) {
        return null;
    }

    @Override
    public void update(Product entity, Integer id) {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
