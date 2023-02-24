package com.examp.demoServiceV2.service;

import com.examp.demoServiceV2.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> getAll();

    T getById(long id) throws ApiException;

    T getByEmail(String email) throws ApiException;

    @Transactional
    T create(T object) throws ApiException;

    @Transactional
    T update(T object) throws ApiException;

    @Transactional
    void delete(T object) throws ApiException;
}
