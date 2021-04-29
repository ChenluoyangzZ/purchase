package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.CategoryMapper;
import com.dingding.purchase.mapper.CategoryMapperCustom;
import com.dingding.purchase.pojo.Category;
import com.dingding.purchase.pojo.vo.ItemVO;
import com.dingding.purchase.pojo.vo.SubCategoryVO;
import com.dingding.purchase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<SubCategoryVO> queryAllSubLevelCat(Integer rootId) {
        return categoryMapperCustom.getSubCatList(rootId);
    }

    @Override
    public List<ItemVO> queryItem(Integer itemId) {
        HashMap<String, Object> itemIdMap = new HashMap<>();
        itemIdMap.put("rootCatId", itemId);
        return categoryMapperCustom.getItemList(itemIdMap);
    }
}