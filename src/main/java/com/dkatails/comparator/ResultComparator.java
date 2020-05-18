package com.dkatails.comparator;

import com.dkatails.data.Pair;
import com.dkatails.data.State;
import com.dkatails.data.TestResult;
import com.dkatails.io.ComparableDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ResultComparator is a generic class.
 * It uses ComparableDataProvider to get comparable data, DataComparator to compare and records the results in TestResult class.
 * @param <T>
 */
public class ResultComparator<T> {

    private static Logger logger = Logger.getLogger(ResultComparator.class.getCanonicalName());

    private final DataComparator<T> dataComparator;
    private final ComparableDataProvider<T> dataProvider;

    private final int parallelism;

    private TestResult<T, State> result;

    public ResultComparator(DataComparator<T> dataComparator, ComparableDataProvider<T> dataProvider) {
        this(dataComparator, dataProvider, 1);
    }

    public ResultComparator(DataComparator<T> dataComparator, ComparableDataProvider<T> dataProvider, int parallelism) {
        this.dataComparator = dataComparator;
        this.dataProvider = dataProvider;
        this.parallelism = parallelism;
        this.result = new TestResult<>();
    }

    public int getParallelism() {
        return parallelism;
    }

    public void execute(){

        /*while (dataProvider.hasData()){
            this.result.addResult(compareData());
        }*/

        ExecutorService service = new ThreadPoolExecutor(parallelism, parallelism,
                100L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        List<Future<Pair<Pair<T, T>, State>>> futureList = new ArrayList<>();

        while (dataProvider.hasData()){
            final Pair<T, T> data = dataProvider.getData();
            futureList.add(service.submit(() -> compareData(data)));
        }

        service.shutdown();

        for(Future<Pair<Pair<T, T>, State>> future : futureList){
            try {
                this.result.addResult(future.get());
            }catch (Exception e){
                logger.log(Level.SEVERE, "Error!!!", e);
            }
        }



    }

    protected Pair<Pair<T, T>, State> compareData(Pair<T, T> data){
        try {
            return new Pair<>(data, dataComparator.compare(data.getX(), data.getY())? State.EQUAL: State.NOT_EQUEAL);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in data comparing.", e);
            //record pair and the error
            return new Pair<>(dataProvider.getCurrentData(), State.ERROR);
        }
    }

    public TestResult<T, State> getTestResult(){
        return this.result;
    }

}
