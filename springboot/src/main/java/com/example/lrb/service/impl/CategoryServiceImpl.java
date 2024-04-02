package com.example.lrb.service.impl;

import com.example.lrb.dao.CategoryDao;
import com.example.lrb.pojo.Category;
import com.example.lrb.service.CategoryService;
import com.example.lrb.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @date 2022-03-20 22:00
 */
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public JsonResult<List<Category>> list(Category query) {
        return JsonResult.ok(categoryDao.selectAll());
    }

    @Override
    public JsonResult<Category> query(Integer id) {
        return null;
    }

    @Override
    public JsonResult<Category> update(Category category) {
        if (category.getId() == null || StringUtils.isBlank(category.getName())) {
            return JsonResult.badRequest("保存失败");
        }
        categoryDao.updateByPrimaryKeySelective(category);
        return JsonResult.ok("保存成功", category);
    }

    @Override
    public JsonResult<Category> insert(Category category) {
        if (StringUtils.isBlank(category.getName())) {
            return JsonResult.badRequest("保存失败");
        }
        categoryDao.insertSelective(category);
        return JsonResult.ok("保存成功", category);
    }

    @Override
    public JsonResult<Category> delete(Integer id) {
        if (id == null) {
            return JsonResult.badRequest("保存失败");
        }
        categoryDao.deleteByPrimaryKey(id);
        return JsonResult.ok("保存成功");
    }
}
