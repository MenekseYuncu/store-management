package com.menekseyuncu.storemanagement.common.model;

import java.util.List;

public interface BaseMapper<S, T> {

    T map(S source);

    List<T> map(List<S> source);
}