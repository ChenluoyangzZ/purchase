package com.dingding.purchase.controller;

import com.dingding.purchase.pojo.ItemsParam;
import com.dingding.purchase.pojo.ItemsSpec;
import com.dingding.purchase.service.ItemParamService;
import com.dingding.purchase.service.ItemSpecService;
import com.dingding.purchase.uitls.RespondResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "商家-商品参数接口")
@RequestMapping("/ItemParam")
@RestController
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;


    @ApiOperation("添加商品")
    @PostMapping(value = "/add")
    public String addItemImg(@RequestBody ItemsParam itemsParam) {
        ItemsParam addItemSpec = new ItemsParam();
        addItemSpec.setId(itemsParam.getId());
        addItemSpec.setBookNumber(itemsParam.getBookNumber());
        addItemSpec.setCreatedTime(new Date());
        addItemSpec.setIsSuit(itemsParam.getIsSuit());
        addItemSpec.setItemId(itemsParam.getItemId());
        addItemSpec.setOpenBook(itemsParam.getOpenBook());
        addItemSpec.setPackingSpecification(itemsParam.getPackingSpecification());
        addItemSpec.setPaper(itemsParam.getPaper());
        itemParamService.add(addItemSpec);
        return RespondResultUtils.ok();
    }

    @ApiOperation("删除商品参数")
    @DeleteMapping("/del")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品参数id",  required = true),
    })
    public String delItem(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        itemParamService.delete(id);
        return RespondResultUtils.ok();
    }

    @ApiOperation("修改商品参数")
    @PutMapping("/update")
    public String updateItem(@RequestBody ItemsSpec itemsSpec) {
        if (StringUtils.isBlank(itemsSpec.getId())) {
            return RespondResultUtils.errorMsg("传入商品参数id空");
        }
        ItemsSpec updateItemSpec = new ItemsSpec();
        updateItemSpec.setId(itemsSpec.getId());
        updateItemSpec.setStock(itemsSpec.getStock());
        updateItemSpec.setUpdatedTime(new Date());
        updateItemSpec.setDiscounts(itemsSpec.getDiscounts());
        updateItemSpec.setPriceDiscount(itemsSpec.getPriceDiscount());
        updateItemSpec.setName(itemsSpec.getName());
        updateItemSpec.setPriceNormal(itemsSpec.getPriceNormal());
        updateItemSpec.setItemId(itemsSpec.getItemId());
//        itemParamService.update(updateItemSpec);
        return RespondResultUtils.ok();
    }

    @ApiOperation("查询商品的参数")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品参数id", example = "1", required = true)
    })
    public String delShopCart(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        return RespondResultUtils.ok(itemParamService.query(id));
    }
}