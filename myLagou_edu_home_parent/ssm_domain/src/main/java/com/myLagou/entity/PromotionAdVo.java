package com.myLagou.entity;

/**
 * @author zhy
 * @create 2022-08-25 16:49
 */
public class PromotionAdVo {
    //当前页
    private Integer currentPage ;
    //每页显示的条数
    private Integer pageSize;

    @Override
    public String toString() {
        return "PromotionAdVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
