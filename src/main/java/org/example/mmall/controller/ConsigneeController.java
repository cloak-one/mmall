package org.example.mmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.model.Consignee;
import org.example.mmall.service.ConsigneeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 收货人详情 前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-08
 */
@RestController
@Tag(name = "ConsigneeController",description = "收货信息管理")
@RequestMapping("/consignee")
public class ConsigneeController {
    @Resource
    private ConsigneeService consigneeService;

    @Operation(summary = "添加地址")
    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public CommonResult NewAddress(@RequestBody @Validated Consignee consignee){
        boolean success = false ;
        if (consignee.getSelected() == 1)
        {
            UpdateWrapper wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id",consignee.getUserId());
            wrapper.eq("selected",1);
            //如果不存在已经选中的地址会返回错误的结果
            Consignee one = consigneeService.getOne(wrapper);
            if (one == null) {
                return CommonResult.failed("添加地址失败");
            }
            wrapper.set("selected",0);
            boolean update = consigneeService.update(wrapper);
            if (update)
                success = consigneeService.save(consignee);
        }else {
            success = consigneeService.save(consignee);
        }
        if (success){
            return CommonResult.success("添加地址成功");
        }else
            return CommonResult.failed("添加地址失败");

    }

    @Operation(summary = "列出地址")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<List<Consignee>> listAddress(@RequestParam @NotNull Long userId){
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Consignee> consigneeList = consigneeService.list(wrapper);
        if (consigneeList == null) {
            return  CommonResult.failed();
        }
        return CommonResult.success(consigneeList);

    }

    @Operation(summary = "更改地址")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult updateAddress(@PathVariable @NotNull Long id,
                                      @RequestBody @Validated Consignee consignee){
        if (id == null ) {
            return CommonResult.failed("更改失败");
        }else{
            consignee.setId(id);
            boolean updated = consigneeService.updateById(consignee);
            if (updated) {
                return CommonResult.success("更改成功");
            }else{
                return CommonResult.failed("更改失败");
            }

        }

    }

    @Operation(summary = "删除地址")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult deleteAddress(@PathVariable @NotNull Long id){
        Consignee byId = consigneeService.getById(id);
        if (byId.getSelected() == 1)
            return CommonResult.failed("默认地址无法删除");
        boolean removed = consigneeService.removeById(id);
        //如果删除的是已选中的地址，会出现无默认地址选中的错误
        if (removed) {
            return CommonResult.success("删除地址成功");
        }else
            return CommonResult.failed();
    }



}
