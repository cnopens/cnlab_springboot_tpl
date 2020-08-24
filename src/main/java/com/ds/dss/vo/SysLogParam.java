package com.ds.dss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("日志请求参数")
public class SysLogParam
{
    @ApiModelProperty(value = "", required = false)
    private String username;
    @ApiModelProperty(value = "", required = false)
    private String operateResult;
    @ApiModelProperty(value = "", required = false)
    private String ip;
    @ApiModelProperty(allowableValues = "1", required = false)
    private int pageNum;
    @ApiModelProperty(allowableValues = "10", required = false)
    private int pageSize;
    @ApiModelProperty(value = "CREATE_DATE DESC", required = false)
    private String orderBy;
    
    public SysLogParam() {
        this.username = "";
        this.operateResult = "";
        this.ip = "";
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public String getOperateResult() {
        return this.operateResult;
    }
    
    public void setOperateResult(final String operateResult) {
        this.operateResult = operateResult;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    

    
    public String getOrderBy() {
        return this.orderBy;
    }
    
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }
}
