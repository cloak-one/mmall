package org.example.mmall.model;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收货人详情
 * </p>
 *
 * @author why
 * @since 2024-02-08
 */
@Getter
@Setter
@Schema(name = "Consignee", description = "收货人详情")
public class Consignee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户id")
    @NotNull
    private Long userId;

    @Schema(description = "用户地址")
    @NotEmpty
    private String userAddress;

    @Schema(description = "用户手机号")
    @NotEmpty
    private String userPhone;

   /* @Schema(description = "订单ID")
    private Long orderId;*/
   @Schema(description = "是否选中，默认为1表示选中，0则未选中")
   @NotNull
   private Integer selected;
}
