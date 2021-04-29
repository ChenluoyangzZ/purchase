package com.dingding.purchase.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "items_param")
public class ItemsParam {
    /**
     * 商品参数id
     */
    @Id
    private String id;

    /**
     * 商品外键id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 开本
     */
    @Column(name = "open_book")
    private String openBook;

    /**
     * 纸张
     */
    private String paper;

    /**
     * 是否套装
     */
    @Column(name = "is_suit")
    private String isSuit;

    /**
     * 国际标准书号ISBN
     */
    @Column(name = "book_number")
    private String bookNumber;

    /**
     * 包装规格
     */
    @Column(name = "packing_specification")
    private String packingSpecification;

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
     * 获取商品参数id
     *
     * @return id - 商品参数id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品参数id
     *
     * @param id 商品参数id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品外键id
     *
     * @return item_id - 商品外键id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品外键id
     *
     * @param itemId 商品外键id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取开本
     *
     * @return open_book - 开本
     */
    public String getOpenBook() {
        return openBook;
    }

    /**
     * 设置开本
     *
     * @param openBook 开本
     */
    public void setOpenBook(String openBook) {
        this.openBook = openBook;
    }

    /**
     * 获取纸张
     *
     * @return paper - 纸张
     */
    public String getPaper() {
        return paper;
    }

    /**
     * 设置纸张
     *
     * @param paper 纸张
     */
    public void setPaper(String paper) {
        this.paper = paper;
    }

    /**
     * 获取是否套装
     *
     * @return is_suit - 是否套装
     */
    public String getIsSuit() {
        return isSuit;
    }

    /**
     * 设置是否套装
     *
     * @param isSuit 是否套装
     */
    public void setIsSuit(String isSuit) {
        this.isSuit = isSuit;
    }

    /**
     * 获取国际标准书号ISBN
     *
     * @return book_number - 国际标准书号ISBN
     */
    public String getBookNumber() {
        return bookNumber;
    }

    /**
     * 设置国际标准书号ISBN
     *
     * @param bookNumber 国际标准书号ISBN
     */
    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    /**
     * 获取包装规格
     *
     * @return packing _specification - 包装规格
     */
    public String getPackingSpecification() {
        return packingSpecification;
    }

    /**
     * 设置包装规格
     *
     * @param packingSpecification 包装规格
     */
    public void setPackingSpecification(String packingSpecification) {
        this.packingSpecification = packingSpecification;
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