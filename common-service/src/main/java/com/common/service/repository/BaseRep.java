package com.common.service.repository;

import com.common.service.dto.PageDTO;
import com.common.service.dto.PageReq;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BaseRep {

    private final DSLContext dsl;

    /**
     * @param clazz       返回实体对象class
     * @param table       查询表
     * @param fields      返回字段数组
     * @param condition   查询条件
     * @param orderFields 排序字段数组
     * @param pageReq     分页组件
     * @param <T>
     * @return
     */
    public <T> PageDTO<T> page(Class<T> clazz, Table<?> table, Field<?>[] fields, Condition condition, List<OrderField<?>> orderFields, PageReq pageReq) {
        // 页条数
        Long total = dsl.selectCount().from(table).where(condition).fetchOne().into(Long.class);
        PageDTO<T> page = new PageDTO<>(Lists.newArrayList(), pageReq, total);
        if (total == 0) {
            return page;
        }
        List<T> datas = dsl.select(fields).from(table).where(condition).orderBy(CollectionUtils.isEmpty(orderFields) ? Lists.newArrayList() : orderFields).limit(pageReq.getSize()).offset((long)(page.getPage() - 1) *  (long)page.getSize()).fetchInto(clazz);
        page.setContent(datas);
        return page;
    }

}
