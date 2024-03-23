package org.example.mmall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonPage;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.service.ProductService;
import org.example.mmall.response.ProductVO;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@RestController
@Tag(name = "ProductController",description = "商品管理")
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * String sortWay;所传输的值为price,sale asc,sale desc,或者不填
     */
    @Operation(summary = "根据二级分类ID获得商品列表")
    @RequestMapping(value = "/listById", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductVO>> getProductById(@RequestParam @NotNull Long categoryId,
                                                        @RequestParam(required = false) String sortWay ,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<ProductVO> productList = productService.listById(categoryId,sortWay,pageSize,pageNum);
        if (productList == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(CommonPage.restPage(productList));
    }


    @Operation(summary = "根据名称获得商品列表")
    @RequestMapping(value = "/listByName", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductVO>> getProductById(@RequestParam @NotBlank String nameKeyword,
                                                              @RequestParam(required = false) String sortWay ,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<ProductVO> productList = productService.listByName(nameKeyword,sortWay,pageSize,pageNum);
        if (productList == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(CommonPage.restPage(productList));
    }



}
