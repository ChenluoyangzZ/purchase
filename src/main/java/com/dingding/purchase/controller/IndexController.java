package com.dingding.purchase.controller;

import com.dingding.purchase.Enum.IsShowEnum;
import com.dingding.purchase.pojo.Carousel;
import com.dingding.purchase.service.CarouselService;
import com.dingding.purchase.service.CategoryService;
import com.dingding.purchase.uitls.RedisUtils;
import com.dingding.purchase.uitls.RespondResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "轮播图,商品分类接口")
@RequestMapping("/index")
@RestController
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;


    @ApiOperation("获取轮播图")
    @GetMapping("/carousel")
    @SneakyThrows
    public String getCarousels() {
        if (StringUtils.isBlank(redisUtils.getKey("carousel"))) {
            List<Carousel> carousels = carouselService.queryCarousels(IsShowEnum.YES.type);
            redisUtils.setKey("carousel", objectMapper.writeValueAsString(carousels));
            return RespondResultUtils.ok(carousels);
        } else {
            return RespondResultUtils.ok(objectMapper.readTree(redisUtils.getKey("carousel")));
        }
    }

    @ApiOperation("获取商品分类一级")
    @GetMapping("/cats")
    public String getCats() {
        return RespondResultUtils.ok(categoryService.queryAllRootLevelCat());
    }

    @ApiOperation("获取子分类")
    @GetMapping("/subCat/{rootCatId}")
    public String getSubCats(
            @ApiParam(value = "传入的id参数", required = true, example = "1")
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return RespondResultUtils.errorMsg("分类不存在");
        }
        return RespondResultUtils.ok(categoryService.queryAllSubLevelCat(rootCatId));
    }

    @ApiOperation("获取一级目录下最新的6个商品")
    @GetMapping("/sixNewItems/{rootCatId}")
    public String getNewItems(
            @ApiParam(value = "传入的id参数", required = true, example = "1")
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return RespondResultUtils.errorMsg("商品不存在");
        }
        return RespondResultUtils.ok(categoryService.queryItem(rootCatId));
    }
}