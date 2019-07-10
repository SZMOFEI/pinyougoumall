package com.pinyougou.sellergoods.service;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojogroup.GoodsDTO;
import entity.PageResult;

import java.util.List;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Goods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Goods goods);

	/**
	 * 增加商品
	*/
	public void add(GoodsDTO goods);
	
	
	/**
	 * 修改
	 */
	public void update(GoodsDTO goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	 public GoodsDTO findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);
	/**
	 * 批量更新状态
	 * @param ids
	 */
	public void updateStatus(Long[] ids,String status);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(Goods goods, int pageNum, int pageSize);
	
}
