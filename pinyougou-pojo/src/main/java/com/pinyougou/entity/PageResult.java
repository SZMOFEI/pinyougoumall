package com.pinyougou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 * @author mofei
 * @date 2018/9/5 10:02
 */
public class PageResult implements Serializable {

    private static final long serialVersionUID = 6270111258866483087L;

    private  Long total;
    private List rows;
    public PageResult(Long total,List rows) {
        super();
        this.total =total;
        this.rows= rows;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
