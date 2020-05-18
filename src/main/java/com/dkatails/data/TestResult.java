package com.dkatails.data;
;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A generic class TestResult holds the results of multiple comparisons.
 * @param <T>
 * @param <R>
 */
public class TestResult<T, R> {

    private List<Pair<Pair<T, T>, R>> rows;

    public TestResult(){
        this.rows = new ArrayList<>();
    }

    public void addResult(Pair<T, T> input, R result){
        rows.add(new Pair<>(input, result));
    }

    public void addResult(Pair<Pair<T, T>, R> row){
        this.rows.add(row);
    }

    public Iterator<Pair<Pair<T, T>, R>> rowIterator(){
        return rows.iterator();
    }

}
