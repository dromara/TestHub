package org.dromara.testhub.framework.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/9 下午12:15
 * @Copyright © 女神帮
 */
@ApiModel
public class PageResult<T> implements IPage<T>, Serializable {
    private static final long serialVersionUID = 8294180014912103005L;
    //每页条数
    @ApiModelProperty(value = "每页条数")
    private long size = 10;
    //当前页页数
    @ApiModelProperty(value = "当前页页数")
    private long current = 1;
    //总页数
    @ApiModelProperty(value = "总页数")
    private long pages;
    //符合条件的总条数
    @ApiModelProperty(value = "符合条件的总条数")
    private long total;
    //排序条件
    private transient List<OrderItem> orderItems = Collections.emptyList();
    //列表数据
    private List<T> records = Collections.emptyList();

    public PageResult() {

    }

    /**
     * 分页
     *
     * @param records      列表数据
     * @param recordsTotal 总记录数
     * @param pageLength   每页记录数
     * @param pageNum      当前页数
     */
    public PageResult(List<T> records, long recordsTotal, long pageLength, long pageNum) {
        this.records = records;
        this.total = recordsTotal;
        this.size = pageLength;
        this.current = pageNum;
        this.pages = (long) Math.ceil((double) recordsTotal / pageLength);
    }

    /**
     * 分页
     */
    public PageResult(Page<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }

    @Override
    @JsonIgnore
    public List<OrderItem> orders() {
        return this.orderItems;
    }

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        if (records == null) {
            this.records = new ArrayList<>();
        } else {
            this.records = records;
        }

        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }
}
