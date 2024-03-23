package org.example.mmall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mmall.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.mmall.response.ProductVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
public interface ProductService extends IService<Product> {



    Page<ProductVO> listById(Long categoryId, String sortWay, Integer pageSize, Integer pageNum);

    Page<ProductVO> listByName(String nameKeyword, String sortWay, Integer pageSize, Integer pageNum);
}
