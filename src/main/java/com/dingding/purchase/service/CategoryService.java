package com.dingding.purchase.service;

import com.dingding.purchase.pojo.Category;
import com.dingding.purchase.pojo.vo.ItemVO;
import com.dingding.purchase.pojo.vo.SubCategoryVO;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    /*
     * 查询所有一级分类
     * @return List<Category>
     */
    public List<Category> queryAllRootLevelCat();
    /*
     * 查询所有一级分类
     * @return List<Category>
     */
    public List<SubCategoryVO> queryAllSubLevelCat(Integer rootId);
    /*
     * 查询所有商品
     * @return List<ItemVO>
     */
    public List<ItemVO> queryItem(Integer rootId);
}
