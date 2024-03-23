package org.example.mmall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.mmall.model.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
