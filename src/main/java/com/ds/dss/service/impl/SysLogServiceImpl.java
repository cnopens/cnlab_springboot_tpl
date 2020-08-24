package com.ds.dss.service.impl;

import com.ds.dss.common.api.ResultPage;
import com.ds.dss.mbg.mapper.DsiSysLogMapper;
import com.ds.dss.mbg.model.DsiSysLog;
import com.ds.dss.service.SysLogService;
import com.ds.dss.vo.SysLogParam;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService
{
    @Autowired
    private DsiSysLogMapper sysLogMapper;
    
    @Override
    public int inertSyslogSelective(final DsiSysLog sysLog) {
        return this.sysLogMapper.insertSelective(sysLog);
    }
    
    @Override
    public ResultPage<DsiSysLog> findSyslogList(final SysLogParam parameter) {
        PageHelper.startPage(parameter.getPageNum(), parameter.getPageSize(), parameter.getOrderBy());
        final List<DsiSysLog> list = (List<DsiSysLog>)this.sysLogMapper.selectSyslogList(parameter);
        return ResultPage.restPage(list);
    }
    
    @Override
    public int removeSysLog(final List<String> list) {
        return this.sysLogMapper.deleteBatchByIds((List)list);
    }
    
    @Override
    public int removeById(final String id) {
        return this.sysLogMapper.deleteByPrimaryKey(BigDecimal.valueOf(Long.valueOf(id)));
    }
}
