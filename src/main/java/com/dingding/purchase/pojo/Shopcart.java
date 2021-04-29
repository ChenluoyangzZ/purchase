package com.dingding.purchase.pojo;

import java.util.Date;
import javax.persistence.*;

public class Shopcart {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商品图片url
     */
    @Column(name = "item_img_url")
    private String itemImgUrl;

    /**
     * 商品名字
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品参数名字
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 打折价格
     */
    @Column(name = "price_discount")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @Column(name = "price_normal")
    private Integer priceNormal;

    /**
     * 商品参数id
     */
    @Column(name = "spec_id")
    private String specId;

    /**
     * 购买数量
     */
    @Column(name = "buy_counts")
    private Integer buyCounts;

    /**
     * 删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取商品图片url
     *
     * @return item_img_url - 商品图片url
     */
    public String getItemImgUrl() {
        return itemImgUrl;
    }

    /**
     * 设置商品图片url
     *
     * @param itemImgUrl 商品图片url
     */
    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    /**
     * 获取商品名字
     *
     * @return item_name - 商品名字
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名字
     *
     * @param itemName 商品名字
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取商品参数名字
     *
     * @return spec_name - 商品参数名字
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 设置商品参数名字
     *
     * @param specName 商品参数名字
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    /**
     * 获取打折价格
     *
     * @return price_discount - 打折价格
     */
    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    /**
     * 设置打折价格
     *
     * @param priceDiscount 打折价格
     */
    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    /**
     * 获取原价
     *
     * @return price_normal - 原价
     */
    public Integer getPriceNormal() {
        return priceNormal;
    }

    /**
     * 设置原价
     *
     * @param priceNormal 原价
     */
    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
    }

    /**
     * 获取商品参数id
     *
     * @return spec_id - 商品参数id
     */
    public String getSpecId() {
        return specId;
    }

    /**
     * 设置商品参数id
     *
     * @param specId 商品参数id
     */
    public void setSpecId(String specId) {
        this.specId = specId;
    }

    /**
     * 获取购买数量
     *
     * @return buy_counts - 购买数量
     */
    public Integer getBuyCounts() {
        return buyCounts;
    }

    /**
     * 设置购买数量
     *
     * @param buyCounts 购买数量
     */
    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    /**
     * 获取删除
     *
     * @return is_delete - 删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置删除
     *
     * @param isDelete 删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}