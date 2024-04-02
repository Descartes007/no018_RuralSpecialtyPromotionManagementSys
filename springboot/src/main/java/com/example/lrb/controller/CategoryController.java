package com.example.lrb.controller;

import com.example.lrb.pojo.Category;
import com.example.lrb.service.CategoryService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @date 2022-03-20 21:59
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 加载所有笔记分类
     *
     * @return 返回对应结果
     */
    @GetMapping("list")
    public JsonResult<List<Category>> list() {
        return categoryService.list(null);
    }

    /**
     * 保存笔记分类
     *
     * @param category 笔记分类信息
     * @return 返回对应结果
     */
    @PostMapping
    public JsonResult<Category> insert(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    /**
     * 更新笔记分类
     *
     * @param category 笔记分类信息
     * @return 返回对应结果
     */
    @PutMapping
    public JsonResult<Category> update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    /**
     * 删除笔记分类
     *
     * @param id 笔记分类ID
     * @return 返回对应结果
     */
    @DeleteMapping("{id}")
    public JsonResult<Category> delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }


}
