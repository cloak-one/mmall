package org.example.mmall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.mmall.model.ProductCategory;
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
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    @Update("UPDATE product_category SET sort = sort + 1 WHERE parent_id = #{parentId} AND sort >= #{sort}")
    void updateSort(Long parentId, Integer sort);

    @Update("UPDATE product_category SET sort = sort + 1 WHERE parent_id = #{parentId} AND sort >= #{sort} AND sort <= #{oldsort}")
    void updateOldSort(Long parentId, Integer sort,Integer oldsort);

    @Update("UPDATE product_category SET sort = sort - 1 WHERE parent_id = #{parentId} AND sort > #{sort}")
    void updateSortMinus(Long parentId, Integer sort);

    @Update("UPDATE product_category SET sort = sort - 1 WHERE parent_id = #{parentId} AND sort >= #{oldsort} AND sort <= #{sort}")
    void updateOldSortMinus(Long parentId, Integer sort, Integer oldsort);
}
