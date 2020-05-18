package com.dkatails.io;

import com.dkatails.data.Pair;

public interface ComparableDataProvider<T> {

    boolean hasData();

    Pair<T, T> getData();

    Pair<T, T> getCurrentData();

}
