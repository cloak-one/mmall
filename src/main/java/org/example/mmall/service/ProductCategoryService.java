package org.example.mmall.service;

import org.example.mmall.model.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mmall.response.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    List<ProductCategoryVO> getAll();

    boolean create(ProductCategory productCategory);

    boolean updateCategory(ProductCategory productCategory);

    boolean removeCategory(Long id);
}
