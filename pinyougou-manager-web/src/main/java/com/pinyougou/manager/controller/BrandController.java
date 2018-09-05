package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import com.pinyougou.pojo.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mofei
 * @date 2018/9/1 22:43
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<Brand> listBrand() {
        return brandService.listBrand();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return brandService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param brand 品牌
     * @return Result
     */
    @RequestMapping("/add")
    public Result addBrand(@RequestBody Brand brand) {
        try {
            brandService.addBrand(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    /**
     * 修改
     *
     * @param brand 品牌
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Brand brand) {

        try {
            brandService.updateBrand(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/findOne")
    public Brand findOne(Long id) {
        return brandService.findOne(id);
    }

    /**
     * 删除
     *
     * @param ids 编号
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.deleteBrand(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
