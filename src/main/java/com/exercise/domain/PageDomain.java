package com.exercise.domain;

import java.util.List;


public class PageDomain {
    private int total;  /*当前页*/
    private int pageNum; /*总页数*/
    private int pageSize; /*页面大小*/
    private int allDataSize; /*总数据条数*/
    private List data;

    public PageDomain(int total, int pageSize, int alldatasize, List data) {
        this.total = total;
        this.pageSize = pageSize;
        this.allDataSize = alldatasize;
        this.data = data;
        pageNum = (int)alldatasize/pageSize + 1;
    }

    public int getTotal() {
        return total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getAllDataSize() {
        return allDataSize;
    }

    public List getData() {
        return data;
    }
}
