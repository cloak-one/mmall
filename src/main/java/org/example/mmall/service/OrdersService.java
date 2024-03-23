package org.example.mmall.service;

import org.example.mmall.model.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
public interface OrdersService extends IService<Orders> {

    Orders orderCreate(Long userId);
}
