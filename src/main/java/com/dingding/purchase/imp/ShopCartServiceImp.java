package com.dingding.purchase.imp;

import com.dingding.purchase.mapper.ShopcartMapper;
import com.dingding.purchase.pojo.Shopcart;
import com.dingding.purchase.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ShopCartServiceImp implements ShopCartService {
    @Autowired
    private ShopcartMapper shopcartMapper;

    @Override
    public boolean queryShopcart(String itemId,String specId,String userId) {
        Example example = new Example(Shopcart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        criteria.andEqualTo("specId",specId);
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("isDelete",0);
        return shopcartMapper.selectCountByExample(example) == 0;
    }

    @Override
    public void addItemToShopCart(Shopcart shopcart) {
    shopcartMapper.insert(shopcart);
    }

    @Override
    public void update(Shopcart shopcart,String itemId,String specId) {
        Example example = new Example(Shopcart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        criteria.andEqualTo("specId",specId);
        shopcartMapper.updateByExampleSelective(shopcart,example);
    }

    @Override
    public List<Shopcart> queryUserALLShopCart(String userId) {
        Example example = new Example(Shopcart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("isDelete",0);
       return shopcartMapper.selectByExample(example);
    }

    @Override
    public Shopcart query(String itemId, String specId,String userId) {
        Example example = new Example(Shopcart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        criteria.andEqualTo("specId",specId);
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("isDelete",0);
        return shopcartMapper.selectOneByExample(example);
    }

    @Override
    public void updateIsDelete(Shopcart shopcart) {
        shopcartMapper.updateByPrimaryKeySelective(shopcart);
    }
}