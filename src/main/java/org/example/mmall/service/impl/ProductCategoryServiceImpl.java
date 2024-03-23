package org.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.mmall.model.ProductCategory;
import org.example.mmall.mapper.ProductCategoryMapper;
import org.example.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mmall.response.ProductCategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public List<ProductCategoryVO> getAll() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        //一级分类
        queryWrapper.eq("parent_id",-1);
        queryWrapper.orderByAsc("sort");
        List<ProductCategory> levelOne = productCategoryMapper.selectList(queryWrapper);
        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e ->
                new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        for (ProductCategoryVO oneVO : levelOneVO) {
            //二级分类
            queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("type",2);
            queryWrapper.eq("parent_id",oneVO.getId());
            queryWrapper.orderByAsc("sort");
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(queryWrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e ->
                    new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
            oneVO.setChildren(levelTwoVO);

        }

        return levelOneVO;
    }

    @Override
    public boolean create(ProductCategory productCategory) {

        productCategoryMapper.updateSort(productCategory.getParentId(),productCategory.getSort());
        return this.save(productCategory);
    }

    @Override
    public boolean updateCategory(ProductCategory productCategory) {
        ProductCategory byId = this.getById(productCategory.getId());
        Integer sort = productCategory.getSort();
        Integer oldsort = byId.getSort();
        if (sort < oldsort)
            productCategoryMapper.updateOldSort(productCategory.getParentId(),sort,oldsort);
        else if (sort > oldsort) {
            productCategoryMapper.updateOldSortMinus(productCategory.getParentId(),sort,oldsort);
        }
        return this.updateById(productCategory);
    }

    @Override
    public boolean removeCategory(Long id) {
        ProductCategory byId = this.getById(id);
        Long parentId = byId.getParentId();
        if (parentId != -1) {
            productCategoryMapper.updateSortMinus(byId.getParentId(),byId.getSort());
            return this.removeById(id);
        }
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        boolean remove = this.remove(wrapper);
        if (!remove) {
            return false;
        }else
            return this.removeById(id);
    }
}
