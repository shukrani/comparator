package com.dkatails;

import com.dkatails.comparator.ResultComparator;

import com.dkatails.comparator.imp.ApiResponseComparator;
import com.dkatails.io.impl.ApiDataProvider;
import com.dkatails.io.impl.ConsoleResultWriter;

/**
 * Main Class to execute JSON comparator.
 * Required to provide two file paths containing HTTP APIs separated by newline character  .
 */
public class JsonComparator {

    public static void main(String[] args) throws Exception{

        String file1 = args[0];
        String file2 = args[1];

        ResultComparator<String> comparator =new ResultComparator<>(new ApiResponseComparator(), new ApiDataProvider(file1,file2), 2);
        comparator.execute();
        ConsoleResultWriter resultWriter = new ConsoleResultWriter();
        resultWriter.print(comparator.getTestResult());
    }

}
