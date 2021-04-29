package com.dingding.purchase.mapper;

import com.dingding.purchase.pojo.vo.ItemCommentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentsMapper {
    public List<ItemCommentsVO> queryAllComments(@Param("paramsMap")Map<String,Object> map);
}