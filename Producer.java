
/**
 * Producer
 */
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Producer {
    static int counter = 0;
    static int counterT = 0;
    static int turnThread = 0;
    public String lock;
    public boolean produce(int val,MyThread o) {
        synchronized (o) {
            Program.print("Thread " + o.threadNumber + " is trying to produce " + val + " at counter " + counter
                +" values of which thread = " + o.start + " and end = " + o.end + " remItr" + o.remItr);
            if(!o.token){
                return false;
            }
            //if thread 1 finished working

            if(val == -1){
                turnThread++;
                o.token = false;
                if(turnThread < Program.nThreads){
                    Program.threads[turnThread].token = true;
                }
                o.notifyAll();
                return true;
            }
            // Program.print("turnThread " + turnThread + " " + o.threadNumber);
            if(counter < Program.bufsize){
                Program.a.add(val);
                counter++;
                return true;
            } else {
                return false;
            }
        }
    }
    public void sleepen(MyThread o){
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}