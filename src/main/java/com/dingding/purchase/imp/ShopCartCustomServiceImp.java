package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ShopCartMapperCustom;
import com.dingding.purchase.pojo.vo.ShopCartVO;
import com.dingding.purchase.service.ShopCartCustomService;
import com.dingding.purchase.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class  ShopCartCustomServiceImp  implements ShopCartCustomService{
    @Autowired
    private ShopCartMapperCustom shopCartMapperCustom;

    @Override
    public List<ShopCartVO> getShopCartByItemId(String specId) {
        String[] split = specId.split(",");
        ArrayList<Object> specIdsList = new ArrayList<>();
        Collections.addAll(specIdsList,split);
        return shopCartMapperCustom.getShopCartList(specIdsList);
    }
}
