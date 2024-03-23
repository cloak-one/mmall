package org.example.mmall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonPage;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.Cart;
import org.example.mmall.service.CartService;
import org.example.mmall.response.CartVO;
import org.example.mmall.request.CartParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@RestController
@Tag(name = "CartController",description = "购物车管理")
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @Operation(description = "添加商品至购物车")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CommonResult addProduct(@RequestBody @Validated CartParam cartParam){
        Cart cart = new Cart();
//        cart.setProductId(cartParam.getProductId());
//        cart.setQuantity(cartParam.getQuantity());
//        cart.setUserId(cartParam.getUserId());
        BeanUtils.copyProperties(cartParam,cart);
        cart.setCost(cartParam.getPrice().multiply(new BigDecimal(cartParam.getQuantity())));

        if (cart.getSelected() != null) {
            cart.setSelected(cart.getSelected());
        }
        boolean save = cartService.save(cart);
        if (save)
            return CommonResult.success(null);
        else
            return CommonResult.failed();
    }

    @Operation(description = "分页查询购物车")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<CartVO>> findCartById(
            @RequestParam @NotNull Long userId,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<CartVO> cartList = cartService.findCartById(userId,pageNum,pageSize);
        if (cartList == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(CommonPage.restPage(cartList));
    }

    @Operation(description = "购物车信息更改")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public CommonResult updateCart(@PathVariable @NotNull Long id,
                                   @RequestParam(required = false) Integer quantity,
                                   @RequestParam(required = false) Integer selected){
        boolean success = cartService.updateCart(id,quantity,selected);
        if (success)
            return CommonResult.success("购物车信息更改成功");
        else
            return CommonResult.failed();
    }

    @Operation(description = "购物车信息通过id删除")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public CommonResult deleteCart(@PathVariable @NotNull Long id){
        boolean success = cartService.removeById(id);
        if (success)
            return CommonResult.success("更改成功");
        else
            return CommonResult.failed();
    }
}
