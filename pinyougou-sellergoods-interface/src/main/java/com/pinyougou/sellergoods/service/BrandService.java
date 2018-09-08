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


    List<Brand> listBrand();

    /**
     *
     * 功能描述: 品牌分页
     *
     * @param: [pageNum, rows]
     * @return: PageResult
     * @auther: mofei
     * @date: 2018/9/5 10:16
     */
    PageResult findPage(Brand brand,int pageNum, int pageSize);
    /**
     *
     * 功能描述: 品牌分页
     *
     * @param: [pageNum, rows]
     * @return: PageResult
     * @auther: mofei
     * @date: 2018/9/5 10:16
     */
    PageResult findPage(int pageNum, int pageSize);

    /**
     * 添加品牌
     * @param brand 品牌信息
     */
    void addBrand(Brand brand);

    /**
     * 修改品牌
     * @param brand 品牌信息
     */
    void updateBrand(Brand brand);

    /**
     * 根据ID查找品牌
     * @param id
     * @return
     */
    Brand findOne(Long id);

    /**
     * 删除
     * @param id 编号
     */
    void deleteBrand(Long[] id);
}
