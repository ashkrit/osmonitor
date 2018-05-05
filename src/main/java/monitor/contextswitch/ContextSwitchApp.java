package monitor.contextswitch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ContextSwitchApp {

    public static final int ITEM_COUNT = 100000 * 10 * 10;

    public static void main(String...args) throws Exception {

        Counter counter = new Counter();
        Queue<Integer> values = new LinkedList<>();
        int threadCount = Integer.parseInt(args[0]);

        for(int x = 0; x< ITEM_COUNT; x++) {
            values.add(x);
        }

        System.out.println("Wait for warm up... Launch pidstat/vmstat to check context switch");
        Thread.sleep(1000*30);
        System.out.println("Wrap up done");




        long start = System.currentTimeMillis();
        Runnable inc = () -> {
            while (true) {
                Integer value;
                synchronized (values) {
                    value = values.poll();
                    if(value==null) break;
                    System.out.println(Thread.currentThread().getName() + " -> Processing task " + value);
                }

                counter.delta(process(value));

            }
        };



        List<Thread> writers = startThreads(inc, threadCount);
        waitForTask(writers);

        long end = System.currentTimeMillis();

        System.out.println(end-start + " \t Sum is " + counter.value());

    }

    private static int process(Integer value) {
        //Expensive calculation
        return value;
    }

    private static void waitForTask(List<Thread> writers) {
        writers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static List<Thread> startThreads(Runnable task, int threadCount) {
        List<Thread> threads = new ArrayList<>();

        for(int count = 0; count < threadCount; count++) {
            threads.add(new Thread(task));
        }

        threads.forEach( t -> t.start());
        return threads;
    }


    static class Counter {

        long counter=0;
        public synchronized long delta(int value) {
            counter += value;
            return counter;
        }

        public synchronized long value() {
            return counter;
        }

    }

}
