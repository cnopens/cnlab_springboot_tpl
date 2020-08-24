package com.ds.dss.mbg.mapper;

import com.ds.dss.mbg.model.DsiPermission;
import com.ds.dss.mbg.model.DsiPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DsiPermissionMapper
{
    long countByExample(final DsiPermissionExample example);
    
    int deleteByExample(final DsiPermissionExample example);
    
    int deleteByPrimaryKey(final BigDecimal id);
    
    int insert(final DsiPermission record);
    
    int insertSelective(final DsiPermission record);
    
    List<DsiPermission> selectByExample(final DsiPermissionExample example);
    
    DsiPermission selectByPrimaryKey(final BigDecimal id);
    
    int updateByExampleSelective(@Param("record") final DsiPermission record, @Param("example") final DsiPermissionExample example);
    
    int updateByExample(@Param("record") final DsiPermission record, @Param("example") final DsiPermissionExample example);
    
    int updateByPrimaryKeySelective(final DsiPermission record);
    
    int updateByPrimaryKey(final DsiPermission record);
}
