package monitor.disk;

import java.io.File;
import java.io.FileOutputStream;

public class DiskWriteApp {

    public static final int ONE_K = 1024;

    public static void main(String...args) throws Exception {

        File f = File.createTempFile(String.valueOf(System.currentTimeMillis()),".temp");
        System.out.println(f);
        byte kb[] = new byte[ONE_K];
        long tengb = (ONE_K * ONE_K* ONE_K)*20L;
        int times  = (int)(tengb/kb.length);

        long start = System.currentTimeMillis();
        try(FileOutputStream fos = new FileOutputStream(f)) {
            for(int count = 0 ; count < times ; count++) {
                fos.write(kb);
            }
        }
        long size = f.length();
        f.deleteOnExit();

        long end = System.currentTimeMillis();

        long total = end-start;
        long totalMBWritten = size/ONE_K;
        float tp = ((totalMBWritten)/(total/1000f))/1024f;

        System.out.println(String.format("Took %s Sec for size %s MB TP  %s MB/sec" , total/1000, totalMBWritten,tp));


    }
}
