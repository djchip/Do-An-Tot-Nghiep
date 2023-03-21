package com.vn.ecommerce.service;

import com.vn.ecommerce.entities.Product;

import java.util.List;

public interface ProductService extends IRootService<Product>{
    List<Product> findAll();
}
