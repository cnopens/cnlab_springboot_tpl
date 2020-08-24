package com.ds.dss.common.api;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResultPage<T>
{
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;
    
    public static <T> ResultPage<T> restPage(final List<T> list) {
        final ResultPage<T> result = new ResultPage<T>();
        final PageInfo<T> pageInfo = (PageInfo<T>)new PageInfo((List)list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
    
    public static <T> ResultPage<T> restPage(final Page<T> pageInfo) {
        final ResultPage<T> result = new ResultPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }
    
    public Integer getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(final Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    public Integer getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getTotalPage() {
        return this.totalPage;
    }
    
    public void setTotalPage(final Integer totalPage) {
        this.totalPage = totalPage;
    }
    
    public List<T> getList() {
        return this.list;
    }
    
    public void setList(final List<T> list) {
        this.list = list;
    }
    
    public Long getTotal() {
        return this.total;
    }
    
    public void setTotal(final Long total) {
        this.total = total;
    }
}
