package org.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.mmall.mapper.ProductMapper;
import org.example.mmall.model.Cart;
import org.example.mmall.mapper.CartMapper;
import org.example.mmall.model.Product;
import org.example.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mmall.response.CartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private ProductMapper productMapper;
    @Override
    public Page<CartVO> findCartById(Long userId, Integer pageNum, Integer pageSize) {
        Page<Cart> cartPage = new Page<>(pageNum,pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
//        wrapper.eq("selected",1);
        Page page = page(cartPage, wrapper);
        if (page == null) {
            return null;
        }
        Page transfer = transfer(page);
        return transfer;
    }

    private Page<CartVO> transfer(Page<Cart> cartPage){
        Page<CartVO> cartVOPage = new Page<>();
        BeanUtils.copyProperties(cartPage,cartVOPage);
        List<CartVO> cartVOS = new ArrayList<>();
        for (Cart cart : cartPage.getRecords()) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVO);
            //先copy product的值，再copy cart的值，避免cart id被覆盖
            BeanUtils.copyProperties(cart,cartVO);
            cartVOS.add(cartVO);
        }
        cartVOPage.setRecords(cartVOS);
        return cartVOPage;
    }

    @Override
    public boolean updateCart(Long id, Integer quantity, Integer selected) {
        if (quantity == null && selected == null) {
            return false;
        }
        BigDecimal price;
        Cart byId = this.getById(id);
//        Cart cart = new Cart();
        if (byId != null) {
//            BeanUtils.copyProperties(byId, cart);
            UpdateWrapper wrapper = new UpdateWrapper<>();
            wrapper.eq("id",id);
            if (quantity != null) {
                price = byId.getCost().divide(new BigDecimal(byId.getQuantity()));
                wrapper.set("quantity",quantity);
                wrapper.set("cost",(price.multiply(new BigDecimal(quantity) ) ) );
            }
            if (selected != null) {
                wrapper.set("selected",selected);
            }
            return this.update(wrapper);
        }else
            return false;

    }
}
