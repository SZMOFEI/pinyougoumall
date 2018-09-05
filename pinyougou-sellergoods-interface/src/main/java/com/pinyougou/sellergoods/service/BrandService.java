package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.Brand;

import java.util.List;

/**
 * 品牌接口
 * @author mofei
 * @date 2018/9/1 22:33
 */
public interface BrandService {


    List<Brand> listBrand ();

    /**
     *
     * 功能描述: 品牌分页
     *
     * @param: [pageNum, rows]
     * @return: com.pinyougou.entity.PageResult
     * @auther: mofei
     * @date: 2018/9/5 10:16
     */
    PageResult  findPage(int pageNum, int pageSize);
}
