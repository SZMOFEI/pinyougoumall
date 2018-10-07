package com.pinyougou.pojogroup;

import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.GoodsDesc;
import com.pinyougou.pojo.Item;

import java.io.Serializable;
import java.util.List;

/**
 * 商品的包装类
 * @author mofei
 * @date 2018/10/7 14:11
 */
public class GoodsDTO implements Serializable {

    private Goods goods;
    private List<Item> itemList;
    private GoodsDesc goodsDesc;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public GoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(GoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
