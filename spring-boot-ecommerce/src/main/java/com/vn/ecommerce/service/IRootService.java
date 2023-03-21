package com.vn.ecommerce.service;

public interface IRootService<T> {
    T create(T entity);

    T retrieve(Integer id);

    void update(T entity, Integer id);

    void delete(Integer id) throws Exception;
}
