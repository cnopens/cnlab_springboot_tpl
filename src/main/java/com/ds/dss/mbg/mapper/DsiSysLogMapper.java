package com.ds.dss.mbg.mapper;

import com.ds.dss.mbg.model.DsiSysLog;
import com.ds.dss.mbg.model.DsiSysLogExample;
import com.ds.dss.vo.SysLogParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DsiSysLogMapper {
    long countByExample(final DsiSysLogExample example);

    int deleteByExample(final DsiSysLogExample example);

    int deleteByPrimaryKey(final BigDecimal id);

    int deleteBatchByIds(final List<String> list);

    int insert(final DsiSysLog record);

    int insertSelective(final DsiSysLog record);

    List<DsiSysLog> selectByExample(final DsiSysLogExample example);

    DsiSysLog selectByPrimaryKey(final BigDecimal id);

    int updateByExampleSelective(@Param("record") final DsiSysLog record, @Param("example") final DsiSysLogExample example);

    int updateByExample(@Param("record") final DsiSysLog record, @Param("example") final DsiSysLogExample example);

    int updateByPrimaryKeySelective(final DsiSysLog record);

    int updateByPrimaryKey(final DsiSysLog record);

    List<DsiSysLog> selectSyslogList(@Param("param") final SysLogParam param);
}
