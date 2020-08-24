package com.ds.dss.service;


import com.ds.dss.common.api.ResultPage;
import com.ds.dss.mbg.model.DsiSysLog;
import com.ds.dss.vo.SysLogParam;

import java.util.List;

public interface SysLogService
{
    int inertSyslogSelective(final DsiSysLog sysLog);
    
    ResultPage<DsiSysLog> findSyslogList(final SysLogParam parameter);
    
    int removeSysLog(final List<String> list);
    
    int removeById(final String id);
}
