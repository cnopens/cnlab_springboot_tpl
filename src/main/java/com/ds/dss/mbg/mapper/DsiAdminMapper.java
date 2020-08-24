package com.ds.dss.mbg.mapper;

import com.ds.dss.mbg.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DsiAdminMapper
{
    long countByExample(final DsiAdminExample example);
    
    int deleteByExample(final DsiAdminExample example);
    
    int deleteByPrimaryKey(final BigDecimal id);
    
    int insert(final DsiAdmin record);
    
    int insertSelective(final DsiAdmin record);
    
    List<DsiAdmin> selectByExample(final DsiAdminExample example);
    
    DsiAdmin selectByPrimaryKey(final BigDecimal id);

    int resetPasswordByPrimaryKey(final BigDecimal id, final String password);
    
    int updateByExampleSelective(@Param("record") final DsiAdmin record, @Param("example") final DsiAdminExample example);
    
    int updateByExample(@Param("record") final DsiAdmin record, @Param("example") final DsiAdminExample example);
    
    int updateByPrimaryKeySelective(final DsiAdmin record);
    
    int updateByPrimaryKey(final DsiAdmin record);

    List<DsiAdmin> getAllAdminUser();

    void updateAdminUserMailNotice(final BigDecimal id, final byte status);
}
