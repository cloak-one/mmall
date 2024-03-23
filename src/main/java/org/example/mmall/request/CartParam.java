package org.example.mmall.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Schema(description = "购物车数据响应对象")
@AllArgsConstructor
@NoArgsConstructor
public class CartParam {
    @NotNull
    @Schema(description = "商品ID")
    private Long productId;

    @NotNull
    @Schema(description = "商品数量")
    private Integer quantity;

    @Schema(description = "商品价格")
    @NotNull
    private BigDecimal price;

    @NotNull
    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "是否选中")
    private Integer selected;

    public CartParam(Long productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public CartParam(Long productId, Integer quantity, BigDecimal price, Long userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }
}
