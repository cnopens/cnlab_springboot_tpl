package com.ds.dss.mbg.mapper;

import com.ds.dss.mbg.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DsiAdminRoleRelationDao
{
    List<DsiPermission> getPermissionList(@Param("adminId") final BigDecimal adminId);
}
