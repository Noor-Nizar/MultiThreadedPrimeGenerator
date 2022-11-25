import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyThread extends Thread {

        public Queue<Integer> q = new LinkedList<Integer>();
        // constructor for class
        int start = 0;
        int end;
        int remItr = 0;
        int threadNumber;
        boolean token = false;
        Producer producer;
        //constructor
        public MyThread(int start, int end, int threadNumber, Producer producer) {
            this.start = start;
            this.end = end;
            this.threadNumber = threadNumber;
            this.producer = producer;
            if(threadNumber == 0){
                token = true;
            }
        }


        public void run() {
            int n;
            for (n = start ; n < end; n++) {
                remItr = end - n;
                boolean isprime = true;
                    for (int j = 2; j < n; j++) {
                            if (n%j == 0){
                                isprime = false;
                                break;
                                  }
                    }
                    if (isprime){
                        q.add(n);
                        if(token){
                            if(producer.produce(q.peek(), this)){
                                q.remove();
                            }
                        }
                    }
            }
            Program.print("thread " + threadNumber + " sleeping");
            if(!token){
                producer.sleepen(this);
            }
            Program.print("thread " + threadNumber + " wokeup");
            while(!q.isEmpty()){
                Program.print("Thread"+ threadNumber+" token = " + token);
                if(token){
                    if(producer.produce(q.peek(), this)){
                        q.remove();
                    }
                }
            }
            producer.produce(-1, this);
        }   
    }

    /*
    0...20  
    20...40
    40...60
    60...80
    80...100
    */
    

