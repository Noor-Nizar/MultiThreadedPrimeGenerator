import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Program {
    static Scanner scan = new Scanner(System.in);
    public static int nThreads, n, bufsize;
    public static Producer[] producers;
    public static Queue<Integer> a;
    public static Hub hub;
    static long startTime;
    static long endTime;
    static long Pcounter=0;
    static long Lprime=0;
    static String FileName;
    public static void run(int _n,int _nThreads,int _bufsize,String _FileName) {
        n=_n;
        nThreads=_nThreads;
        bufsize=_bufsize;
        FileName=_FileName;
        a = new LinkedList<Integer>();
        startTime = System.currentTimeMillis();
        Hub.reset();
        hub = new Hub();
        producers = new Producer[nThreads];
        for (int i = 0; i < nThreads; i++) {
            if(i==0)
                producers[i] = new Producer((i*n/nThreads)+2, Math.min((i+1)*n/nThreads,n ), i, hub);
            else
                producers[i] = new Producer((i*n/nThreads), Math.min((i+1)*n/nThreads,n ), i, hub);
        }

        Consumer consumer = new Consumer(hub);
        consumer.start();
        // start all producers
        for (int i = 0; i < nThreads; i++) {
            producers[i].start();
        }
        
       
        // wait for all producers to finish
        for (int i = 0; i < nThreads; i++) {
            try {
                producers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // wait for consumer to finish
        consumer.work = false;
        try {
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.work = true;
        // print the result
        // while(!a.isEmpty()){
        //     System.out.println(a.remove());
        // }
        // for (int i = 0; i < bufsize; i++){
        //     System.out.println(a[i]);
        // }

        endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
    public static void print(String x){
        System.out.println(x);
    }
    public static long getTime(){
        return endTime-startTime;
    }
    
}