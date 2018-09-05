package com.pinyougou.mapper;

import com.pinyougou.pojo.FreightTemplate;
import com.pinyougou.pojo.FreightTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreightTemplateMapper {
    int countByExample(FreightTemplateExample example);

    int deleteByExample(FreightTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FreightTemplate record);

    int insertSelective(FreightTemplate record);

    List<FreightTemplate> selectByExample(FreightTemplateExample example);

    FreightTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByExample(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByPrimaryKeySelective(FreightTemplate record);

    int updateByPrimaryKey(FreightTemplate record);
}