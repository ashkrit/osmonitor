package monitor.disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DiskReadValueApp {

    public static void main(String...args) throws Exception {

        File f = new File("/tmp/data","test.dat");
        System.out.println(f);
        byte kb[] = new byte[1024];
        int mb = 1024 * 1024 * 10;
        long value = 0;
        long start = System.currentTimeMillis();
        try(FileInputStream fos = new FileInputStream(f)) {
            for(int count = 0 ; count < mb ; count++) {
                fos.read(kb);
                value+=kb[0];
            }
        }
        long size = f.length();


        long end = System.currentTimeMillis();

        long total = end-start;
        long totalMBWritten = size/1024L;
        float tp = ((totalMBWritten)/(total/1000f))/1024f;

        System.out.println(String.format("Took %s Sec for size %s MB TP  %s MB/sec , value %s" , total/1000, totalMBWritten,tp,value));

    }
}
