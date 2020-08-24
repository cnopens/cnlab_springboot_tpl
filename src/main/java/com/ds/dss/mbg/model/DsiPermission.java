package com.ds.dss.mbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DsiPermission implements Serializable
{
    private BigDecimal id;
    private BigDecimal pid;
    private String name;
    private String value;
    private String icon;
    private Long type;
    private String uri;
    private Long status;
    private Date createTime;
    private Long sort;
    private static final long serialVersionUID = 1L;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(final BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getPid() {
        return this.pid;
    }
    
    public void setPid(final BigDecimal pid) {
        this.pid = pid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(final String icon) {
        this.icon = icon;
    }
    
    public Long getType() {
        return this.type;
    }
    
    public void setType(final Long type) {
        this.type = type;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(final String uri) {
        this.uri = uri;
    }
    
    public Long getStatus() {
        return this.status;
    }
    
    public void setStatus(final Long status) {
        this.status = status;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public Long getSort() {
        return this.sort;
    }
    
    public void setSort(final Long sort) {
        this.sort = sort;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", pid=").append(this.pid);
        sb.append(", name=").append(this.name);
        sb.append(", value=").append(this.value);
        sb.append(", icon=").append(this.icon);
        sb.append(", type=").append(this.type);
        sb.append(", uri=").append(this.uri);
        sb.append(", status=").append(this.status);
        sb.append(", createTime=").append(this.createTime);
        sb.append(", sort=").append(this.sort);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}
