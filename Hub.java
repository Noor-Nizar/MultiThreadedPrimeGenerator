import java.util.LinkedList;
import java.util.Queue;

/**
 * Producer
 */
class synchoPrinter {
    public void print(String x) {
        System.out.println(x);
    }
}

public class Hub {
    static int counter = 0;
    static boolean writing = false;
    static int turnThread = 0;
    Object Lock = new Object();
    synchoPrinter sp = new synchoPrinter();

    public boolean add(int val, Producer o) {
        synchronized (this) {
            // print("Thread " + o.threadNumber + " is trying to add " + val + " at counter
            // " + counter
            // +" values of which thread = " + o.start + " and end = " + o.end + " remItr" +
            // o.remItr);
            if (!o.token) {
                // print("exit");
                return false;
            }
            // if thread 1 finished working

            if (val == -1) {
                turnThread++;
                o.token = false;
                if (turnThread < Program.nThreads) {
                    Program.producers[turnThread].token = true;
                }
                // Program.print("notifying all");
                notifyAll();
                return true;
            }
            // Program.print("turnThread " + turnThread + " " + o.threadNumber);
            if (counter < Program.bufsize) {
                Program.a.add(val);
                counter++;
                synchronized (Lock){
                    try{print("sleeping");wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public void sleepen(Producer o) {
        synchronized (this) {
            // Program.print("Thread " + o.threadNumber + " is trying to sleep");
            if (o.token) {
                return;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(String x) {
        synchronized (sp) {
            Program.print(x);
        }
    }

    public Queue<Integer> consume() {
        print("before this");
        synchronized (this) {
            Queue<Integer> q = new LinkedList<Integer>();

            while(counter > 0){
                int next = Program.a.remove();
                q.add(next);
                counter--;
            }
            print("cant enter lock");
            synchronized (Lock){
                print("notifying all");
                notify();
            }
            return q;
        }
    }
}