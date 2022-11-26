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
    public static void main(String[] args) {
        n = scan.nextInt();
        nThreads = scan.nextInt();
        bufsize = scan.nextInt();
        //queue of size buffersize
        a = new LinkedList<Integer>();
        // initialize a list of producers using number of producers and passing constructor
        // values n, buf
        //calculate time of execution
        long startTime = System.currentTimeMillis();
        hub = new Hub();
        producers = new Producer[nThreads];
        for (int i = 0; i < nThreads; i++) {
            producers[i] = new Producer(i*n/nThreads, Math.min((i+1)*n/nThreads,n ), i, hub);
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
        // print the result
        // while(!a.isEmpty()){
        //     System.out.println(a.remove());
        // }
        // for (int i = 0; i < bufsize; i++){
        //     System.out.println(a[i]);
        // }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
    public static void print(String x){
        System.out.println(x);
    }
    
}