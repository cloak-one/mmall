package org.example.mmall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mmall.response.api.ResultCode;
import org.example.mmall.exception.Asserts;
import org.example.mmall.model.Product;
import org.example.mmall.mapper.ProductMapper;
import org.example.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mmall.response.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Page<ProductVO> listById(Long categoryId, String sortWay, Integer pageSize, Integer pageNum) {
        Page<Product> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if(categoryId!=null){
            wrapper.eq("categoryleveltwo_id",categoryId);
        }
        return getProductVOPage(sortWay, page, wrapper);

    }

    private Page<ProductVO> getProductVOPage(String sortWay, Page<Product> page, QueryWrapper<Product> wrapper) {
        if(!StrUtil.isNotEmpty(sortWay)){
            return transfer(page(page, wrapper));
        }
        /*if ("sale".equals(sortWay))
            wrapper.orderByDesc("sales_number");
        else if (sortWay.startsWith("price ")){
            String str = sortWay.substring("price ".length());
            if ("asc".equals(str))
                wrapper.orderByAsc("price");
            else if ("desc".equals(str))
                wrapper.orderByDesc("price");
            else
                Asserts.fail(ResultCode.PARAMS_ERROR);
        }else
            Asserts.fail(ResultCode.PARAMS_ERROR);*/
        switch (sortWay) {
            case "sale":
                wrapper.orderByDesc("sales_number");
                break;
            case "price asc":
                wrapper.orderByAsc("price");
                break;
            case "price desc":
                wrapper.orderByDesc("price");
                break;
            default:
                Asserts.fail(ResultCode.PARAMS_ERROR);
        }
        return transfer(page(page, wrapper));
    }

    @Override
    public Page<ProductVO> listByName(String nameKeyword, String sortWay, Integer pageSize, Integer pageNum) {
        Page<Product> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(nameKeyword)){
            wrapper.like("name",nameKeyword);
        }
        return getProductVOPage(sortWay, page, wrapper);
    }

    private Page<ProductVO> transfer(Page<Product> productPage){
        Page<ProductVO> productVOPage = new Page<>();
        BeanUtils.copyProperties(productPage,productVOPage);
        List<ProductVO> productVOS = productPage.getRecords().stream().map(e ->
                        new ProductVO(e.getId(),e.getName(),e.getPrice(),e.getSalesNumber()))
                .collect(Collectors.toList());
        productVOPage.setRecords(productVOS);
        return productVOPage;
    }
}
