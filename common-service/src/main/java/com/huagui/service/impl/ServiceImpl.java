package com.huagui.service.impl;

/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.jooq.UpdatableRecord;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * IService 实现类（ 泛型：M 是 dao 对象，T 是实体 ）
 *
 * @author cx
 */
@SuppressWarnings("unchecked")
public abstract class ServiceImpl<M extends DAOImpl<R, T, String>, T, R extends UpdatableRecord<R>> {

    @Autowired
    public M baseDao;

    public T save(T t) {
        baseDao.insert(t);
        return t;
    }

    public List<T> save(List<T> t) {
        baseDao.insert();
        return t;
    }

    public T update(T t) {
        baseDao.update(t);
        return t;
    }

    public List<T> update(List<T> t) {
        baseDao.update(t);
        return t;
    }

    public List<T> findAll() {
        return baseDao.findAll();
    }

    public T findById(String id) {
        return baseDao.findById(id);
    }

    public void deleteById(String id) {
        baseDao.deleteById(id);
    }

    public void deleteById(List<String> id) {
        baseDao.deleteById(id);
    }
}
