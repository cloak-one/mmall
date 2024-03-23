package org.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.example.mmall.mapper.CartMapper;
import org.example.mmall.mapper.ConsigneeMapper;
import org.example.mmall.model.Cart;
import org.example.mmall.model.Consignee;
import org.example.mmall.model.OrderDetail;
import org.example.mmall.model.Orders;
import org.example.mmall.mapper.OrdersMapper;
import org.example.mmall.service.OrderDetailService;
import org.example.mmall.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private ConsigneeMapper mapper;
    @Resource
    OrderDetailService orderDetailService;


    @Override
    public Orders orderCreate(Long userId) {
        /*
        1. 从购物车中查询
        2. 生成订单（从地址管理中得到收货人信息）
        3. （订单详情）
        4. 从购物车中逻辑删除被选中的
         */
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("selected",1);
        BigDecimal costAll = new BigDecimal("0.00");
        List<Cart> cartlist = cartMapper.selectList(wrapper);
        if (cartlist == null || cartlist.isEmpty()) {
            return null;
        }
        Orders orders = new Orders();
        for (Cart cart : cartlist) {
            costAll = costAll.add(cart.getCost());
        }

        Consignee consignee = mapper.selectOne(wrapper);
        if (consignee == null) {
            return null;
        }
        orders.setCost(costAll);
        BeanUtils.copyProperties(consignee,orders);
        orders.setId(null);
        /*orders.setUserPhone(consignee.getUserPhone());
        orders.setUserAddress(consignee.getUserAddress());*/
        if (!this.save(orders)) {
            return null;
        }
        List<OrderDetail> detailList = cartlist.stream().map(e ->
                        new OrderDetail(e.getCost().divide(new BigDecimal(e.getQuantity())),
                                e.getProductId(),orders.getId(),e.getQuantity(),e.getCost()))
                .collect(Collectors.toList());
        boolean saveBatch = orderDetailService.saveBatch(detailList);
        if (!saveBatch) {
            return null;
        }

        int delete = cartMapper.delete(wrapper);
        if (delete > 0)
            return orders;
        else return null;

    }
}
