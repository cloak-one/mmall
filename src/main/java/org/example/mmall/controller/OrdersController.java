package org.example.mmall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.Orders;
import org.example.mmall.service.OrdersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@RestController
@RequestMapping("/order")
@Tag(description = "订单生成",name = "OrdersController")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @Operation(summary = "根据用户ID创建订单")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public CommonResult<Orders> orderCreate(@RequestParam @NotNull Long userId){
        Orders orders = ordersService.orderCreate(userId);
        if (orders != null) {
            return CommonResult.success(orders);
        }else
            return CommonResult.failed();
    }
}
