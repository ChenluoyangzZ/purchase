package com.dingding.purchase.controller;

import com.dingding.purchase.pojo.ItemsSpec;
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

@Api(tags = "商家-商品规格接口")
@RequestMapping("/ItemSpec")
@RestController
public class ItemSpecController {

    @Autowired
    private ItemSpecService itemSpecService;


    @ApiOperation("添加商品规格")
    @PostMapping(value = "/add")
    public String addItemImg(@RequestBody ItemsSpec itemsSpec) {
        ItemsSpec addItemSpec = new ItemsSpec();
        addItemSpec.setId(itemsSpec.getId());
        addItemSpec.setStock(itemsSpec.getStock());
        addItemSpec.setCreatedTime(new Date());
        addItemSpec.setDiscounts(itemsSpec.getDiscounts());
        addItemSpec.setPriceDiscount(itemsSpec.getPriceDiscount());
        addItemSpec.setName(itemsSpec.getName());
        addItemSpec.setPriceNormal(itemsSpec.getPriceNormal());
        addItemSpec.setItemId(itemsSpec.getItemId());
        itemSpecService.add(addItemSpec);
        return RespondResultUtils.ok();
    }

    @ApiOperation("删除商品规格")
    @DeleteMapping("/del")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品参数id",  required = true),
    })
    public String delItem(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        itemSpecService.delete(id);
        return RespondResultUtils.ok();
    }

    @ApiOperation("修改商品规格")
    @PutMapping("/update")
    public String updateItem(@RequestBody ItemsSpec itemsSpec) {
        if (StringUtils.isBlank(itemsSpec.getId())) {
            return RespondResultUtils.errorMsg("传入商品参数id空");
        }
        ItemsSpec addItemSpec = new ItemsSpec();
        addItemSpec.setId(itemsSpec.getId());
        addItemSpec.setStock(itemsSpec.getStock());
        addItemSpec.setUpdatedTime(new Date());
        addItemSpec.setDiscounts(itemsSpec.getDiscounts());
        addItemSpec.setPriceDiscount(itemsSpec.getPriceDiscount());
        addItemSpec.setName(itemsSpec.getName());
        addItemSpec.setPriceNormal(itemsSpec.getPriceNormal());
        addItemSpec.setItemId(itemsSpec.getItemId());
        itemSpecService.update(addItemSpec);
        return RespondResultUtils.ok();
    }

    @ApiOperation("查询商品的规格")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品参数id", example = "1", required = true)
    })
    public String delShopCart(@RequestParam String id) {
        if (StringUtils.isBlank(id)) {
            return RespondResultUtils.errorMsg("参数不能为空");
        }
        return RespondResultUtils.ok(itemSpecService.query(id));
    }
}