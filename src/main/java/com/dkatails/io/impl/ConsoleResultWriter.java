package com.dkatails.io.impl;

import com.dkatails.data.Pair;
import com.dkatails.data.State;
import com.dkatails.data.TestResult;
import com.dkatails.io.ResultWriter;

import java.util.Iterator;

/**
 * ConsoleResultWriter dumps the TestResult to console.
 */
public class ConsoleResultWriter implements ResultWriter<TestResult<String, State>> {

    @Override
    public void print(TestResult<String, State> result) {

        Iterator<Pair<Pair<String, String>, State>> iterator = result.rowIterator();

        if(iterator != null){
            while (iterator.hasNext()){
                Pair<Pair<String, String>, State> row = iterator.next();
                if(State.ERROR != row.getY()){
                    System.out.println(row.getX().getX() + " " + row.getY().getValue() + " " + row.getX().getY());
                }
            }

        }

    }
}
