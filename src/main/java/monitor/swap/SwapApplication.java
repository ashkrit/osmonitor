package monitor.swap;

import java.util.LinkedList;
import java.util.List;

/*
    Run multiple instance of this application

    java -Xmx1g -cp target/classes monitor.swap.SwapApplication

    then start vmstat to check swap in/out stats

    vmstat 5



 */

public class SwapApplication {

    public static final int ONE_MB = 1024 * 1024;
    public static final int ONE_GB = ONE_MB * 1024;

    public static void main(String...args) throws Exception {


        List<byte[]> values = new LinkedList<>();
        values.add(new byte[ONE_GB]);
        values.add(new byte[ONE_GB]);

        System.out.println("Wait now ");
        Thread.sleep(1000 * 60);
        System.out.println("Done now " + values.size());



    }
}
