#Comparator

# Prerequisite
1. **Language:** Java 8
2. **Build Tool:** Apache Maven (version 3.2.5+)


# How To Use
    
1. ComparableDataProvider<T>: is a generic interface, responsible to get and return data to be compared. It is a data reader.
    
    example of ComparableDataProvider is `com.dkatails.io.impl.ApiDataProvider`
    
2. DataComparator<T>: is a generic interface, responsible to compare the two inputs of type T.
    
    example of DataComparator is `com.dkatails.imp.ApiResponseComparator`.
    
3. ResultComparator<T>: is a generic class, it depends on ComparableDataProvider<T> and DataComparator<T> to get comparable data and use DataComparator to compare.
                        It stores the result to TestResult object.
                        
4. TestResult<T>: is a generic class and holds the result of comparisons in a List.

5. ResultWriter<T>: is a generic class to dump the comparison result hold by ResultWriter.
        com.dkatails.io.impl.ConsoleResultWriter is use to dump result on console.

Example:
```
        DataComparator<String> comparator = new ApiResponseComparator();
        ComparableDataProvider<String> dataProvider = new ApiDataProvider(file1,file2);
        int parallelism = 2; // parallelism can be set to run comparisons in parallel.

        ResultComparator<String> comparator =new ResultComparator<>(comparator, dataProvider, parallelism);
        comparator.execute();

        ConsoleResultWriter resultWriter = new ConsoleResultWriter();
        resultWriter.print(comparator.getTestResult());

```
