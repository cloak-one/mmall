package org.example.mmall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "购物车数据传输对象")
@NoArgsConstructor
public class CartVO implements Serializable {
    @Schema(description = "购物车id")
    private Long id;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品价格")
    private BigDecimal price;

    @Schema(description = "商品数量")
    private Integer quantity;

    @Schema(description = "商品花费")
    private BigDecimal cost;

    public CartVO(Long id, Long productId, Integer quantity, BigDecimal cost) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }

    //    private Integer selected;

}
