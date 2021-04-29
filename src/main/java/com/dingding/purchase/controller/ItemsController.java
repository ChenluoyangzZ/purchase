package com.dingding.purchase.controller;

import com.dingding.purchase.pojo.Items;
import com.dingding.purchase.pojo.vo.ItemInfoVO;
import com.dingding.purchase.service.ItemsService;
import com.dingding.purchase.service.ShopCartCustomService;
import com.dingding.purchase.service.ShopCartService;
import com.dingding.purchase.uitls.RespondResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "前台接口")
@RequestMapping("/items")
@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ShopCartCustomService shopCartCustomService;

    public static final Integer MIX_PAGE = 1;
    public static final Integer MAX_PAGE = 10;

    @ApiOperation("获取商品")
    @GetMapping("/info/{itemId}")
    public String getItems(
            @ApiParam(value = "传入的id参数", required = true, example = "1")
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return RespondResultUtils.errorMsg("传入商品id不存在");
        }
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(itemsService.queryItemById(itemId));
        itemInfoVO.setItemImgList(itemsService.queryItemImgById(itemId));
        itemInfoVO.setItemSpecList(itemsService.queryItemSpecById(itemId));
        itemInfoVO.setItemParams(itemsService.queryItemParamById(itemId));
        return RespondResultUtils.ok(itemInfoVO);
    }


    @ApiOperation("获取评论数目")
    @GetMapping("/commentLevel")
    public String getCounts(
            @ApiParam(value = "传入的id参数", required = true)
            @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return RespondResultUtils.errorMsg("传入评论id不存在");
        }
        return RespondResultUtils.ok(itemsService.queryItemCommentById(itemId));
    }

    @ApiOperation("获取评论")
    @GetMapping("/comments")
    public String getComments(
            @ApiParam(value = "传入的id参数", required = true)
            @RequestParam String itemId, Integer level, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return RespondResultUtils.errorMsg("传入评论id不存在");
        }
        if (page == null) {
            page = MIX_PAGE;
        }
        if (pageSize == null) {
            page = MAX_PAGE;
        }

        return RespondResultUtils.ok(itemsService.queryAllItemCommentById(itemId, level, page, pageSize));
    }

    @ApiOperation("搜索商品")
    @GetMapping("/search")
    public String searchItems(
            @ApiParam(value = "传入的搜索内容", required = true)
            @RequestParam String keywords, String sort, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return RespondResultUtils.errorMsg("搜寻内容不能为空");
        }
        if (page == null) {
            page = MIX_PAGE;
        }
        if (pageSize == null) {
            page = MAX_PAGE;
        }

        return RespondResultUtils.ok(itemsService.queryAllItemSearchById(keywords, sort, page, pageSize));
    }

    @ApiOperation("用过分类搜索商品")
    @GetMapping("/catItems")
    public String searchItemsByCatId(
            @ApiParam(value = "传入的搜索内容", required = true)
            @RequestParam Integer catId, String sort, Integer page, Integer pageSize) {
        if (catId == null) {
            return RespondResultUtils.errorMsg("搜寻内容不能为空");
        }
        if (page == null) {
            page = MIX_PAGE;
        }
        if (pageSize == null) {
            page = MAX_PAGE;
        }

        return RespondResultUtils.ok(itemsService.queryAllItemSearchByCatId(catId, sort, page, pageSize));
    }


    @ApiOperation("商品添加到购物车")
    @GetMapping("/refresh")
    public String addShopCartBySpecId(
            @ApiParam(value = "传入的搜索内容", required = true)
            @RequestParam String itemSpecIds) {
        System.out.println(itemSpecIds);
        if (StringUtils.isBlank(itemSpecIds)) {
            return RespondResultUtils.errorMsg("传入参数为空");
        }
        return RespondResultUtils.ok(shopCartCustomService.getShopCartByItemId(itemSpecIds));
    }

    @ApiOperation("添加商品")
    @PostMapping("/add")
    public String addItems(@RequestBody Items items) {
        if (StringUtils.isBlank(items.getId())) {
            return RespondResultUtils.errorMsg("传入参数为空");
        }
        Items newItems = new Items();
        newItems.setId(items.getId());
        newItems.setSellCounts(items.getSellCounts());
        newItems.setCatId(items.getCatId());
        newItems.setContent(items.getContent());
        newItems.setCreatedTime(new Date());
        newItems.setRootCatId(items.getRootCatId());
        newItems.setItemName(items.getItemName());
        itemsService.addItems(newItems);
        return RespondResultUtils.ok();
    }
}