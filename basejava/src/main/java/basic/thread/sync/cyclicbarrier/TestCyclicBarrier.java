package basic.thread.sync.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by liubo on 16/7/19.
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        final  int rows = 10000;
        final int number = 1000;
        final int search = 5;
        final  int participant = 5;
        final int line_participant = 2000;

        MatrixMock mock  = new MatrixMock(rows,number,search);
        Results results = new Results(rows);
        Grouper grouper = new Grouper(results);

        CyclicBarrier barrier = new CyclicBarrier(participant,grouper);
        Searcher[] searchers = new Searcher[participant];
        for (int i = 0; i < participant; i++) {
            searchers[i] = new Searcher(barrier,i*line_participant,(i*line_participant)+line_participant,mock,results,5);
            new Thread(searchers[i],"Thread "+i).start();
        }
    }
}
