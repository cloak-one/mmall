package org.example.mmall.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Schema(name = "Order", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "客户ID")
    private Long userId;

    @Schema(description = "总价")
    private BigDecimal cost;

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT)
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @Schema(description = "配送方式")
    private String shipping;

    @Schema(description = "订单状态1-4分别代表待支付，已取消，已完成，已退款")
    private Integer status;

    @Schema(description = "用户地址")
    private String userAddress;

    @Schema(description = "用户手机号")
    private String userPhone;

}
