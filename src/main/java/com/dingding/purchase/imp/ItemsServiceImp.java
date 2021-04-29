package com.dingding.purchase.imp;

import com.dingding.purchase.Enum.CommentLevelEnum;
import com.dingding.purchase.mapper.*;
import com.dingding.purchase.pojo.*;
import com.dingding.purchase.pojo.vo.ItemCommentsVO;
import com.dingding.purchase.pojo.vo.ItemCountsVO;
import com.dingding.purchase.pojo.vo.SearchVO;
import com.dingding.purchase.service.ItemsService;
import com.dingding.purchase.uitls.StringReplaceUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemsServiceImp implements ItemsService {
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private SearchMapper searchMapper;
//    @Autowired
//    private ItemsSpecMapperCustom itemsSpecMapperCustom;

    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<ItemsImg> queryItemImgById(String itemId) {
        Example example = genExample(ItemsImg.class, "itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Override
    public List<ItemsSpec> queryItemSpecById(String itemId) {
        Example example = genExample(ItemsSpec.class, "itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsParam queryItemParamById(String itemId) {
        Example example = genExample(ItemsParam.class, "itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public ItemCountsVO queryItemCommentById(String itemId) {
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        itemsComments.setCommentLevel(CommentLevelEnum.GOOD.type);

        int goodComment = itemsCommentsMapper.selectCount(itemsComments);
        itemsComments.setCommentLevel(CommentLevelEnum.NORMAL.type);
        int normalComment = itemsCommentsMapper.selectCount(itemsComments);
        itemsComments.setCommentLevel(CommentLevelEnum.BAD.type);
        int badComment = itemsCommentsMapper.selectCount(itemsComments);

        ItemCountsVO itemCommentVO = new ItemCountsVO();
        itemCommentVO.setTotalCounts(goodComment + normalComment + badComment);
        itemCommentVO.setGoodCounts(goodComment);
        itemCommentVO.setNormalCounts(normalComment);
        itemCommentVO.setBadCounts(badComment);
        return itemCommentVO;
    }

    @Override
    public PageGridResult queryAllItemCommentById(String itemId, Integer level, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);
        PageHelper.startPage(page, pageSize);
        List<ItemCommentsVO> itemCommentsVOS = commentsMapper.queryAllComments(map);
        for (ItemCommentsVO itemComment : itemCommentsVOS) {
            itemComment.setNickname(StringReplaceUtil.userNameReplaceWithStar(itemComment.getNickname()));
        }
        PageInfo<?> pageInfo = new PageInfo<>(itemCommentsVOS);
        return genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(), itemCommentsVOS);
    }

    @Override
    public PageGridResult queryAllItemSearchById(String keywords, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchVO> searchVO = searchMapper.getSearchList(map);
        PageInfo<?> pageInfo = new PageInfo<>(searchVO);
        return genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(), searchVO);
    }

    @Override
    public PageGridResult queryAllItemSearchByCatId(Integer catId, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchVO> searchVO = searchMapper.getSearchListByCatId(map);
        PageInfo<?> pageInfo = new PageInfo<>(searchVO);
        return genPageGridResult(page, pageInfo.getPages(), pageInfo.getTotal(), searchVO);
    }

    @Override
    public ItemsSpec queryItemsSpecById(String id) {
        return itemsSpecMapper.selectByPrimaryKey(id);
    }

    @Override
    public ItemsImg queryItemMainImgById(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        criteria.andEqualTo("isMain", 1);
        return itemsImgMapper.selectOneByExample(example);
    }

    @Override
    public void updateItemsSpecById(String specId, int pendingCounts) {
        ItemsSpec itemsSpec = itemsSpecMapper.selectByPrimaryKey(specId);
        itemsSpec.setStock(itemsSpec.getStock() - pendingCounts);
        itemsSpecMapper.updateByPrimaryKeySelective(itemsSpec);
        Items items=new Items();
        items.setId(itemsSpec.getItemId());
        Items updateItems = itemsMapper.selectByPrimaryKey(items);
        items.setSellCounts(updateItems.getSellCounts()+pendingCounts);
        itemsMapper.updateByPrimaryKeySelective(items);
    }

    public Example genExample(java.lang.Class<?> entityClass, String property, String param) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(property, param);
        return example;
    }

    public PageGridResult genPageGridResult(int page, int total, long records, List<?> rows) {
        PageGridResult pageGridResult = new PageGridResult();
        pageGridResult.setPage(page);
        pageGridResult.setTotal(total);
        pageGridResult.setRecords(records);
        pageGridResult.setRows(rows);
        return pageGridResult;
    }

    @Override
    public void addItems(Items items) {
        itemsMapper.insert(items);
    }
}