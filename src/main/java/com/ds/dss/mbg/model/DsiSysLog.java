package com.ds.dss.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DsiSysLog implements Serializable
{
    private BigDecimal id;
    private Integer userId;
    private String username;
    private String peration;
    private String method;
    private String params;
    private String ip;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    private String operateResult;
    private String abnormity;
    private Integer spendtime;
    private String uri;
    private static final long serialVersionUID = 1L;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(final BigDecimal id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPeration() {
        return this.peration;
    }
    
    public void setPeration(final String peration) {
        this.peration = peration;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(final String method) {
        this.method = method;
    }
    
    public String getParams() {
        return this.params;
    }
    
    public void setParams(final String params) {
        this.params = params;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }
    
    public String getOperateResult() {
        return this.operateResult;
    }
    
    public void setOperateResult(final String operateResult) {
        this.operateResult = operateResult;
    }
    
    public String getAbnormity() {
        return this.abnormity;
    }
    
    public void setAbnormity(final String abnormity) {
        this.abnormity = abnormity;
    }
    
    public Integer getSpendtime() {
        return this.spendtime;
    }
    
    public void setSpendtime(final Integer spendtime) {
        this.spendtime = spendtime;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(final String uri) {
        this.uri = uri;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", userId=").append(this.userId);
        sb.append(", username=").append(this.username);
        sb.append(", peration=").append(this.peration);
        sb.append(", method=").append(this.method);
        sb.append(", params=").append(this.params);
        sb.append(", ip=").append(this.ip);
        sb.append(", createDate=").append(this.createDate);
        sb.append(", operateResult=").append(this.operateResult);
        sb.append(", abnormity=").append(this.abnormity);
        sb.append(", spendtime=").append(this.spendtime);
        sb.append(", uri=").append(this.uri);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}
