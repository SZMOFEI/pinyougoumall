package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TypeTemplate;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface TypeTemplateService {

	/**
	 * 返回全部列表
	 * @return List<TypeTemplate>
	 */
	public List<TypeTemplate> findAll();
	/**
	 * 下拉
	 * @return List<Map>
	 */
	List<Map> selectOptionList ();
	
	/**
	 * 返回分页列表
	 * @return PageResult
	 */
	 PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	 void add(TypeTemplate typeTemplate);
	
	
	/**
	 * 修改
	 */
	 void update(TypeTemplate typeTemplate);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	 TypeTemplate findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TypeTemplate typeTemplate, int pageNum, int pageSize);
	
}
