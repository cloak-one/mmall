package org.example.mmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.OrderDetail;
import org.example.mmall.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@RestController
@RequestMapping("/orderDetail")
@Tag(name = "OrderDetailController",description = "订单详情管理")
public class OrderDetailController {
    @Resource
    private OrderDetailService orderDetailService;

    @Operation(summary = "根据订单ID查询订单详情")
    @RequestMapping(method = RequestMethod.GET)
    public CommonResult<List<OrderDetail>> detailList(@RequestParam @NotNull Long orderId){

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",orderId);
        List<OrderDetail> detailList = orderDetailService.list(wrapper);
        if (detailList == null || detailList.isEmpty()) {
            return CommonResult.failed();
        }
        return CommonResult.success(detailList);
    }
}
