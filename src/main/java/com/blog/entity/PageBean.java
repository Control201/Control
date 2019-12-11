package com.blog.entity;

/**
 * 页面分页类
 */
public class PageBean {
    private int page;//当前页数
    private int pageSize;//页面大小
    private int start;//第几条数据开始查询


    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (this.page - 1) * this.pageSize;
    }

}
