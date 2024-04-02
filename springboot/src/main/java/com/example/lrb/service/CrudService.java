package com.example.lrb.service;

import com.example.lrb.vo.JsonResult;

import java.util.List;

public interface CrudService<T> {
    JsonResult<List<T>> list(T query);

    JsonResult<T> query(Integer id);

    JsonResult<T> update(T t);

    JsonResult<T> insert(T t);

    JsonResult<T> delete(Integer id);

}
