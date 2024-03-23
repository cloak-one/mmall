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
@Schema(name = "Product", description = "商品")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品描述")
    private String description;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "销售量")
    private Integer salesNumber;

    @Schema(description = "版本号")
    private Integer version;

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

//    @Schema(description = "一级分类ID")
//    private Long categoryleveloneId;

    @Schema(description = "二级分类ID")
    private Long categoryleveltwoId;

}
