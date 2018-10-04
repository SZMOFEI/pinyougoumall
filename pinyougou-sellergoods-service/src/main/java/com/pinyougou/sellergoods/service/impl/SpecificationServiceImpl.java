package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationExample;
import com.pinyougou.pojo.SpecificationExample.Criteria;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.pojo.SpecificationOptionExample;
import com.pinyougou.pojogroup.SpecificationVo;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private SpecificationMapper specificationMapper;
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Specification> page=   (Page<Specification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SpecificationVo vo) {
		specificationMapper.insert(vo.getSpecification());
		for (SpecificationOption specificationOption : vo.getSpecificationOptionList()) {
			Long id = vo.getSpecification().getId();
			specificationOption.setSpecId(id);
			specificationOptionMapper.insert(specificationOption);
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SpecificationVo vo){
		specificationMapper.updateByPrimaryKey(vo.getSpecification());
		SpecificationOptionExample optionExample = new SpecificationOptionExample();
		SpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
		criteria.andSpecIdEqualTo(vo.getSpecification().getId());
		specificationOptionMapper.deleteByExample(optionExample);

		for (SpecificationOption specificationOption : vo.getSpecificationOptionList()) {
			specificationOption.setSpecId(vo.getSpecification().getId());
			specificationOptionMapper.insert(specificationOption);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SpecificationVo findOne(Long id){
		Specification specification = specificationMapper.selectByPrimaryKey(id);

		SpecificationOptionExample optionExample = new SpecificationOptionExample();
		SpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<SpecificationOption> optionList = specificationOptionMapper.selectByExample(optionExample);
		SpecificationVo vo = new SpecificationVo();
		vo.setSpecification(specification);
		vo.setSpecificationOptionList(optionList);
		return vo;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			SpecificationOptionExample optionExample = new SpecificationOptionExample();
			SpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(optionExample);
		}		
	}
	
	
		@Override
	public PageResult findPage(Specification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SpecificationExample example=new SpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<Specification> page= (Page<Specification>)specificationMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
