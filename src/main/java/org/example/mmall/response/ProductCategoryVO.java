package org.example.mmall.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品分类数据传输对象")
public class ProductCategoryVO implements Serializable {
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "商品分类名称")
    private String name;

    @Schema(description = "商品的子分类")
    private List<ProductCategoryVO> children;

    public ProductCategoryVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
