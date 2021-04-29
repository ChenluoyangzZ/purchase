package com.dingding.purchase.controller;

import com.dingding.purchase.pojo.ItemsImg;
import com.dingding.purchase.service.ItemImgService;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.dingding.purchase.uitls.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Api(tags = "商家-商品图片接口")
@RequestMapping("/myItemImg")
@RestController
public class ItemImgController {

    @Autowired
    private ItemImgService itemImgServic;
    private final String FILEPATH = "E:\\Apache Tomcat\\" + File.separator + "webapps" + File.separator + "shop" +
            File.separator + "images" + File.separator + "items";

    @ApiOperation("添加商品图片")
    @PostMapping(value = "/add")
    @SneakyThrows
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "60", required = true),
            @ApiImplicitParam(name = "id", value = "商品图片id", example = "61", required = true),
            @ApiImplicitParam(name = "isMain", value = "是否为主图", example = "1", required = true),
            @ApiImplicitParam(name = "sort", value = "商品页商品图片顺序", example = "0", required = true)
    })
    public String addItemImg(@RequestParam String itemId,
                             @RequestParam String id,
                             @RequestParam int isMain,
                             @RequestParam int sort, MultipartFile multipartFile) {
        if (StringUtils.isBlank(itemId) || multipartFile.isEmpty()) {
            return RespondResultUtils.errorMsg("传入商品图片");
        }
        String fileName = UploadUtils.uploadFile(itemId, FILEPATH, multipartFile, "item");
        ItemsImg newItemsImg = new ItemsImg();
        newItemsImg.setId(id);
        newItemsImg.setUrl(fileName);
        newItemsImg.setCreatedTime(new Date());
        newItemsImg.setIsMain(isMain);
        newItemsImg.setSort(sort);
        newItemsImg.setItemId(itemId);
        itemImgServic.addItemImg(newItemsImg);
        return RespondResultUtils.ok();
    }

    @ApiOperation("删除商品图片")
    @DeleteMapping("/del")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "60", required = true),
            @ApiImplicitParam(name = "id", value = "商品图片id", example = "61", required = true),
    })
    public String delItem(@RequestParam String id, @RequestParam String itemId) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(itemId)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        itemImgServic.deleteItemImg(id, itemId);
        return RespondResultUtils.ok();
    }

    @ApiOperation("更新商品图片")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "60", required = true),
            @ApiImplicitParam(name = "id", value = "商品图片id", example = "61", required = true),
            @ApiImplicitParam(name = "isMain", value = "是否为主图", example = "1", required = true),
            @ApiImplicitParam(name = "sort", value = "商品页商品图片顺序", example = "0", required = true),
            @ApiImplicitParam(name = "url", value = "商品图片链接", required = true)
    })
    public String updateItem(@RequestParam String itemId,
                             @RequestParam String id,
                             @RequestParam int isMain,
                             @RequestParam int sort,
                             @RequestParam String url) {
        if (StringUtils.isBlank(itemId) || StringUtils.isBlank(id)) {
            return RespondResultUtils.errorMsg("传入商品图片id为空或者传入商品id为空");
        }
        ItemsImg newItemsImg = new ItemsImg();
        newItemsImg.setId(id);
        newItemsImg.setUrl(url);
        newItemsImg.setUpdatedTime(new Date());
        newItemsImg.setIsMain(isMain);
        newItemsImg.setSort(sort);
        newItemsImg.setItemId(itemId);
        itemImgServic.updateItemImg(newItemsImg);
        return RespondResultUtils.ok();
    }

    @ApiOperation("查询商品的全部图片")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", example = "60", required = true)
    })
    public String delShopCart(@RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        return RespondResultUtils.ok(itemImgServic.queryItemsImg(itemId));
    }
}