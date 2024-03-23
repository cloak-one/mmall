package org.example.mmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_category")
@Schema(name = "ProductCategory", description = "商品一级二级分类")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "商品名称")
    @NotEmpty
    private String name;

    @NotNull
    @Schema(description = "上级分类的编号：0表示一级分类")
    private Long parentId;

//    @NotNull
//    @Schema(description = "分类级别")
//    private Integer type;

    @NotNull
    @Schema(description = "排序级别")
    private Integer sort;

}
