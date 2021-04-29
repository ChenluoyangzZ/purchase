package com.dingding.purchase.mapper;

import com.dingding.purchase.pojo.vo.ItemVO;
import com.dingding.purchase.pojo.vo.SubCategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    public List<SubCategoryVO>  getSubCatList(Integer rootCatId);

    public List<ItemVO> getItemList(@Param("paramsMap") Map<String,Object> map);
}
