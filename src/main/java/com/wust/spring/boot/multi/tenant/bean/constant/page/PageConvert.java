package com.wust.spring.boot.multi.tenant.bean.constant.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wust.spring.boot.multi.tenant.bean.constant.StringUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageConvert {

    /**
     * @param pageRequire can't be null
     */
    public static <T> Page<T> PageRequire2MybatisPlusPage(PageRequire pageRequire) {
        Page<T> page = new Page<T>()
                .setCurrent(pageRequire.getPage())
                .setSize(pageRequire.getPageSize());
        if (pageRequire.getOrders() != null) {
            List<String> items = pageRequire.getOrders().getItems();
            List<Boolean> ascs = pageRequire.getOrders().getAscs();
            for (int i = 0; i < items.size(); i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(StringUtils.camelCaseToSnakeCase(items.get(i)));
                orderItem.setAsc(ascs.get(i));
                page.addOrder(orderItem);
            }
        }
        return page;
    }

    public static <T, R> PageResult<R> MybatisPlusPage2PageResult(Page<T> pageResult, Function<T, R> function) {
        return new PageResult<R>()
                .setTotal(pageResult.getTotal())
                .setPage(pageResult.getCurrent())
                .setPageSize(pageResult.getSize())
                .setRecords(pageResult.getRecords().stream()
                        .map(function)
                        .collect(Collectors.toList()));
    }

    public static <T> PageResult<T> MybatisPlusPage2PageResult(Page<T> pageResult) {
        return new PageResult<T>()
                .setTotal(pageResult.getTotal())
                .setPage(pageResult.getCurrent())
                .setPageSize(pageResult.getSize())
                .setRecords(pageResult.getRecords());
    }
}
