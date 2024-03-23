package org.example.mmall.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * <p>
 * 购物车
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Chart", description = "购物车")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "花费")
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

    @Schema(description = "客户ID")
    private Long userId;

    @Schema(description = "是否选中，默认为1表示选中，0则未选中")
    private Integer selected;

}
