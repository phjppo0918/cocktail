package com.cocktail.common;

import java.util.List;

public interface InitSvc<T, ID> {
    T create(T t);
    List<T> createAll(List<T> ts);
    List<T> getAll();
    T getById(ID id);
    T updateById(ID id, T t);
    T deleteById(ID id);
}
