package org.example.mmall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.ProductCategory;
import org.example.mmall.service.ProductCategoryService;
import org.example.mmall.response.ProductCategoryVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Controller
@Tag(name = "ProductCategoryController",description = "商品分类管理")
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Resource
    private ProductCategoryService categoryService;

    @Operation(summary = "获取分类信息")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult< List<ProductCategoryVO> > getCategory(){
        List<ProductCategoryVO> all = categoryService.getAll();
        return CommonResult.success(all);
    }

    @Operation(summary = "添加商品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @Validated ProductCategory productCategory) {
        boolean success = categoryService.create(productCategory);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable @NotNull Long id,
                               @RequestBody @Validated ProductCategory productCategory) {
        if(productCategory.getParentId().equals(id))
            return CommonResult.failed("商品父级不能指向自己");
        productCategory.setId(id);
        boolean success = categoryService.updateCategory(productCategory);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "根据ID删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {

        boolean success = categoryService.removeCategory(id);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }


}
