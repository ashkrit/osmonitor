package monitor.top;

import java.util.Random;

public class TopApplication {


    public static void main(String...args) {

        int noOfThreads = args.length > 0 ? Integer.parseInt(args[0]) : Runtime.getRuntime().availableProcessors()+2;

        Runnable r = () -> {
            long value = 0;
            Random rnd = new Random();
            while (true) {


                for (int times = 0; times < 10000_000; times++) {
                    value += Math.sqrt(rnd.nextDouble() * 1000000);
                }

                System.out.println(value);

            }
        };

        for(int t=0;t<noOfThreads;t++) {
            new Thread(r).start();
        }


    }
}
