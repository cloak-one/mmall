package org.example.mmall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mmall.model.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mmall.response.CartVO;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
public interface CartService extends IService<Cart> {

    Page<CartVO> findCartById(Long userId, Integer pageNum, Integer pageSize);

    boolean updateCart(Long id, Integer quantity, Integer selected);
}
