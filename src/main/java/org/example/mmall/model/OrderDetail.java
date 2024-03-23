package org.example.mmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Getter
@Setter
@TableName("order_detail")
@Schema(name = "OrderDetail", description = "")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "花费")
    private BigDecimal cost;

    public OrderDetail(BigDecimal price, Long productId, Long orderId, Integer quantity, BigDecimal cost) {
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.cost = cost;
    }
}
