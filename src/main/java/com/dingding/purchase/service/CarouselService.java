package com.dingding.purchase.service;

import com.dingding.purchase.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    public List<Carousel> queryCarousels(Integer isShow);
}
