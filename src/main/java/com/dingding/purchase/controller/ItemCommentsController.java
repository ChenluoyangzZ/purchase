package com.dingding.purchase.controller;

import com.dingding.purchase.Enum.CommentLevelEnum;
import com.dingding.purchase.pojo.ItemsComments;
import com.dingding.purchase.service.ItemCommentService;
import com.dingding.purchase.uitls.RespondResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "商家-商品评论接口")
@RequestMapping("/myItemComment")
@RestController
public class ItemCommentsController {

    @Autowired
    private ItemCommentService itemCommentService;

    @ApiOperation("删除商品评论")
    @DeleteMapping("/del")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "1", required = true),
            @ApiImplicitParam(name = "id", value = "商品图片id", example = "1", required = true)
    })
    public String delItemComment(@RequestParam String id,
                                 @RequestParam String itemId) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(itemId)) {
           return RespondResultUtils.errorMsg("参数不能为空");
        }
        itemCommentService.deleteItemComments(id, itemId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("更新商品评论")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "1", required = true),
            @ApiImplicitParam(name = "id", value = "商品图片id", example = "1", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", example = "210323A90G38WDP0", required = true),
            @ApiImplicitParam(name = "content", value = "商品评论内容", example = "太好了", required = true),
            @ApiImplicitParam(name = "commentLevel", value = "商品评论等级", example = "1", required = true)
    })
    public String updateItemComment(@RequestParam String itemId,
                                    @RequestParam String userId,
                                    @RequestParam String id,
                                    @RequestParam String content,
                                    @RequestParam int commentLevel) {
        if (StringUtils.isBlank(itemId) || StringUtils.isBlank(userId)) {
          return   RespondResultUtils.errorMsg("参数不能为空");
        }
        ItemsComments newItemsComments = new ItemsComments();
        if (commentLevel == CommentLevelEnum.GOOD.type) {
            newItemsComments.setContent(content);
            newItemsComments.setCommentLevel(CommentLevelEnum.GOOD.type);
            newItemsComments.setUpdatedTime(new Date());
        }
        if (commentLevel == CommentLevelEnum.NORMAL.type) {
            newItemsComments.setContent(content);
            newItemsComments.setCommentLevel(CommentLevelEnum.NORMAL.type);
            newItemsComments.setUpdatedTime(new Date());
        }
        if (commentLevel == CommentLevelEnum.BAD.type) {
            newItemsComments.setContent(content);
            newItemsComments.setCommentLevel(CommentLevelEnum.BAD.type);
            newItemsComments.setUpdatedTime(new Date());
        }
        itemCommentService.updateItemComments(newItemsComments, itemId, userId, id);
        return RespondResultUtils.ok();
    }

    @ApiOperation("查询商品评价")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "1", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", example = "210323A90G38WDP0", required = true)
    })
    public String delShopCart(@RequestParam String itemId, @RequestParam String userId) {
        if (StringUtils.isBlank(itemId) || StringUtils.isBlank(userId)) {
         return    RespondResultUtils.errorMsg("参数不能为空");
        }
        return RespondResultUtils.ok(itemCommentService.queryItemsComments(userId, itemId));
    }
}