package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.GoodsExample.Criteria;
import com.pinyougou.pojogroup.GoodsDTO;
import com.pinyougou.sellergoods.service.GoodsService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDescMapper goodsDescMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Goods goods) {
        goodsMapper.insert(goods);
    }

    /**
     * 增加商品
     *
     * @param dto 包装类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GoodsDTO dto) {
        //设置为申请状态
        dto.getGoods().setAuditStatus("0");
        goodsMapper.insert(dto.getGoods());
        dto.getGoodsDesc().setGoodsId(dto.getGoods().getId());
        goodsDescMapper.insert(dto.getGoodsDesc());

        saveItemList(dto);

    }

    private void saveItemList(GoodsDTO dto) {
        if ("1".equals(dto.getGoods().getIsEnableSpec())) {
            List<Item> itemList = dto.getItemList();
            for (Item item : itemList) {
                String title = dto.getGoods().getGoodsName();
                //将一个字符串转成一个map
                Map<String, Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title += " " + specMap.get(key);
                }
                item.setTitle(title);
                setItemValue(dto, item);
                itemMapper.insert(item);
            }
        } else {
            Item item = new Item();
            //商品KPU+规格描述串作为SKU名称
            item.setTitle(dto.getGoods().getGoodsName());
            //价格
            item.setPrice(dto.getGoods().getPrice());
            //状态
            item.setStatus("1");
            //是否默认
            item.setIsDefault("1");
            //库存数量
            item.setNum(99999);
            item.setSpec("{}");
            setItemValue(dto, item);
            itemMapper.insert(item);
        }
    }

    private void setItemValue(GoodsDTO dto, Item item) {
        //商品spu编号
        item.setGoodsId(dto.getGoods().getId());
        item.setSellerId(dto.getGoods().getSellerId());
        item.setCategoryid(dto.getGoods().getCategory3Id());
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        //品牌名称
        Brand brand = brandMapper.selectByPrimaryKey(dto.getGoods().getBrandId());
        item.setBrand(brand.getName());
        //分类名称
        ItemCat itemCat = itemCatMapper.selectByPrimaryKey(dto.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());
        //商家名称
        Seller seller = sellerMapper.selectByPrimaryKey(dto.getGoods().getSellerId());
        item.setSeller(seller.getNickName());
        //图片地址
        List<Map> images = JSON.parseArray(dto.getGoodsDesc().getItemImages(), Map.class);
        if (images.size() > 0) {
            item.setImage((String) images.get(0).get("url"));
        }
    }


    /**
     * 修改
     */
    @Override
    public void update(GoodsDTO dto) {
        //设置为申请状态
        dto.getGoods().setAuditStatus("0");
        goodsMapper.updateByPrimaryKey(dto.getGoods());
        goodsDescMapper.updateByPrimaryKey(dto.getGoodsDesc());

        //删除原来的sku
        ItemExample example =new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(dto.getGoods().getId());
        itemMapper.deleteByExample(example);
        saveItemList(dto);

    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public GoodsDTO findOne(Long id) {
        GoodsDTO dto = new GoodsDTO();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        dto.setGoods(goods);
        GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        dto.setGoodsDesc(goodsDesc);

        //查找规格选项
        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goods.getId());
        List<Item> items = itemMapper.selectByExample(example);
        dto.setItemList(items);
        return dto;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        GoodsExample example = new GoodsExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        List<Goods> goods = goodsMapper.selectByExample(example);
        for (Goods good : goods) {
            good.setIsDelete("1");
            goodsMapper.updateByPrimaryKey(good);
        }
    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        for (Long id : ids) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setAuditStatus(status);
            goodsMapper.updateByPrimaryKey(goods);
        }
    }


    @Override
    public PageResult findPage(Goods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        GoodsExample example = new GoodsExample();
        Criteria criteria = example.createCriteria();

        if (goods != null) {
            if (goods.getSellerId() != null && goods.getSellerId().length() > 0) {
                criteria.andSellerIdEqualTo(goods.getSellerId());
            }
            if (goods.getGoodsName() != null && goods.getGoodsName().length() > 0) {
                criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            }
            if (goods.getAuditStatus() != null && goods.getAuditStatus().length() > 0) {
                criteria.andAuditStatusLike("%" + goods.getAuditStatus() + "%");
            }
            if (goods.getIsMarketable() != null && goods.getIsMarketable().length() > 0) {
                criteria.andIsMarketableLike("%" + goods.getIsMarketable() + "%");
            }
            if (goods.getCaption() != null && goods.getCaption().length() > 0) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (goods.getSmallPic() != null && goods.getSmallPic().length() > 0) {
                criteria.andSmallPicLike("%" + goods.getSmallPic() + "%");
            }
            if (goods.getIsEnableSpec() != null && goods.getIsEnableSpec().length() > 0) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            if (goods.getIsDelete() != null && goods.getIsDelete().length() > 0) {
                criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            }
            criteria.andIsDeleteIsNull();//非删除状态
        }

        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
