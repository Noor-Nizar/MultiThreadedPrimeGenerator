
/**
 * Producer
 */

public class Hub {
    static int counter = 0;
    static int counterT = 0;
    static int turnThread = 0;
    public Object lock = new Object();
    public  boolean add(int val,Producer o) {
        synchronized (lock) {
            // print("Thread " + o.threadNumber + " is trying to add " + val + " at counter " + counter
            //     +" values of which thread = " + o.start + " and end = " + o.end + " remItr" + o.remItr);
            if(!o.token){
                // print("exit");
                return false;
            }
            //if thread 1 finished working

            if(val == -1){
                turnThread++;
                o.token = false;
                if(turnThread < Program.nThreads){
                    Program.producers[turnThread].token = true;
                }
                // Program.print("notifying all");
                notifyAll();
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
    public void sleepen(Producer o){
        synchronized (lock) {
            // Program.print("Thread " + o.threadNumber + " is trying to sleep");
            if(o.token){
                return;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(String x){
        synchronized (lock){
            Program.print(x);
        }
    }
}