package com.dingding.purchase.service;

import com.dingding.purchase.pojo.*;
import com.dingding.purchase.pojo.vo.ItemCommentsVO;
import com.dingding.purchase.pojo.vo.ItemCountsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsService {
    /*
     * 根据商品id查询商品信息
     * */
    public Items queryItemById(String itemId);

    /*
     * 根据商品id查询商品图片列表
     * */
    public List<ItemsImg> queryItemImgById(String itemId);
    /*
     * 根据商品id查询商品主图片
     * */
    public ItemsImg queryItemMainImgById(String itemId);


    /*
     * 根据商品id查询商品规格
     * */
    public List<ItemsSpec> queryItemSpecById(String itemId);

    /*
     * 根据商品id查询商品参数
     * */
    public ItemsParam queryItemParamById(String itemId);
    /*
     * 根据商品id查询商品评论条数
     * */
    public ItemCountsVO queryItemCommentById(String itemId);
    /*
     * 根据商品id查询商品评论
     * */
    public PageGridResult queryAllItemCommentById(String itemId,Integer level,Integer page,Integer pageSize);
    /*
     * 根据商品id搜索商品
     * */
    public PageGridResult queryAllItemSearchById(String keywords, String sort, Integer page, Integer pageSize);
    /*
     * 根据商品id搜索商品
     * */
    public PageGridResult queryAllItemSearchByCatId(Integer catId, String sort, Integer page, Integer pageSize);

    /*
     * 根据商品配置id查询配置
     * */
    public ItemsSpec queryItemsSpecById(String id);

    /*
     * 根据商品配置id查询配置
     * */
    public void updateItemsSpecById(String specId,int pendingCounts);

    /*
     * 添加商品
     * */
    public void addItems(Items items);
}