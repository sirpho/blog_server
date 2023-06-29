package com.sirpho.blog.common.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sirpho.blog.common.enums.ConstantEnum;
import com.sirpho.blog.common.xss.SQLFilter;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.initPage(params);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        Page<T> page = this.initPage(params);

        if (CollectionUtils.isNotEmpty(page.orders())) {
            return page;
        }

        //没有排序字段，则不排序
        if (!StringUtils.hasLength(defaultOrderField)) {
            return page;
        }
        return order(page, defaultOrderField, isAsc);
    }

    public IPage<T> getPage(Map<String, Object> params, OrderItem... defaultOrderItems) {
        return getPage(params, Stream.of(defaultOrderItems).collect(Collectors.toList()));
    }

    public IPage<T> getPage(Map<String, Object> params, List<OrderItem> defaultOrderItems) {
        Page<T> page = this.initPage(params);

        if (CollectionUtils.isNotEmpty(page.orders())) {
            return page;
        }

        //没有排序字段，则不排序
        if (CollectionUtils.isEmpty(defaultOrderItems)) {
            return page;
        }
        return order(page, defaultOrderItems);
    }

    private Page<T> initPage(Map<String, Object> params) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        Object pageObj = params.get(ConstantEnum.PAGE_CURRENT.getValue());
        Object limitObj = params.get(ConstantEnum.PAGE_SIZE.getValue());

        if (pageObj != null && !"".equals(pageObj.toString().trim())) {
            curPage = Long.parseLong(pageObj.toString().trim());
        }
        if (limitObj != null && !"".equals(limitObj.toString().trim())) {
            limit = Long.parseLong(limitObj.toString().trim());
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(ConstantEnum.PAGE_CURRENT.getValue(), page);

        //排序字段
        //防止SQL注入（ORDER_FIELD、ORDER是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = (String) params.get(ConstantEnum.ORDER_FIELD.getValue());
        String order = (String) params.get(ConstantEnum.ORDER.getValue());

        //前端字段排序
        if (StringUtils.hasLength(orderField) && StringUtils.hasLength(order)) {
            orderField = SQLFilter.sqlInject(CommonUtils.toSnakeCase(orderField));
            order(page, orderField, ConstantEnum.ORDER_ASC.getValue().equalsIgnoreCase(order));
        }

        return page;
    }

    private Page<T> order(Page<T> page, String orderField, boolean isAsc) {
        return order(page, Stream.of(orderField).filter(StringUtils::hasLength).map(s -> new OrderItem(s, isAsc)).collect(Collectors.toList()));
    }

    private Page<T> order(Page<T> page, List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            return page;
        }
        return page.addOrder(orderItems);
    }
}
