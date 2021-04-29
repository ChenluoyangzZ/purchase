package com.dingding.purchase.mapper;

import com.dingding.purchase.pojo.vo.SearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SearchMapper {
    public List<SearchVO> getSearchList(@Param("paramsMap") Map<String, Object> map);

    public List<SearchVO> getSearchListByCatId(@Param("paramsMap") Map<String, Object> map);
}
