package com.ds.dss.service;



import com.ds.dss.common.api.Result;
import com.ds.dss.common.api.ResultPage;
import com.ds.dss.mbg.model.DsiAdmin;
import com.ds.dss.mbg.model.DsiAdminExample;
import com.ds.dss.mbg.model.DsiPermission;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService
{
    DsiAdmin getAdminByUsername(final String username);
    
    DsiAdmin register(final DsiAdmin dsiAdminParam);
    
    int update(final DsiAdmin dsiAdminParam);
    
    int delete(final BigDecimal id);
    
    int changePasss(final DsiAdmin dsiAdminParam, final DsiAdminExample dsiAdminExample);
    
    String login(final String username, final String password);

    Result modifyPassword(final BigDecimal userID, final String oriPassword, final String newPassword);
    
    List<DsiPermission> getPermissionList(final BigDecimal adminId);
    
    ResultPage<DsiAdmin> list(final Integer page, final Integer pageSize, final String name);

    List<DsiAdmin> getAllAdminUser();

    void updateAdminUserMailNotice(final BigDecimal userID, final byte status);
}
