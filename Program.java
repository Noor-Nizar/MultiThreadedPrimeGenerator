import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Program {
    static Scanner scan = new Scanner(System.in);
    public static int nThreads, n, bufsize;
    public static MyThread[] threads;
    public static Queue<Integer> a;
    public static Producer producer;
    public static void main(String[] args) {
        n = scan.nextInt();
        nThreads = scan.nextInt();
        bufsize = scan.nextInt();
        //queue of size buffersize
        a = new LinkedList<Integer>();
        // initialize a list of threads using number of threads and passing constructor
        // values n, buf
        //calculate time of execution
        long startTime = System.currentTimeMillis();
        producer = new Producer();
        threads = new MyThread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new MyThread(i*n/nThreads, Math.min((i+1)*n/nThreads,n ), i, producer);
        }
        // start all threads
        for (int i = 0; i < nThreads; i++) {
            threads[i].start();
        }
        // wait for all threads to finish
        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // print the result
        while(!a.isEmpty()){
            System.out.println(a.remove());
        }
        // for (int i = 0; i < bufsize; i++){
        //     System.out.println(a[i]);
        // }
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println("Counter " + Producer.counter);
    }
    public static void print(String x){
        System.out.println(x);
    }
}