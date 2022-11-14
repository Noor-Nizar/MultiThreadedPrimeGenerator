import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyThread extends Thread {

        public Queue<Integer> q = new LinkedList<Integer>();
        // constructor for class
        int start = 0;
        int end;
        int step;
        int bufferSize;
        int counter;
    
        //constructor
        public MyThread(int start, int end, int step, int bufferSize) {
            this.start = start;
            this.end = end;
            this.step = step;
            this.bufferSize = bufferSize;
            this.counter = start;
        }


        public void run() {
            int n;
            for (n = start+2 ; n <= end; n+=step) {
                boolean isprime = true;
                    for (int j = 2; j < n; j++) {
                            if (n%j == 0){
                                isprime = false;
                                break;
                                  }
                    }
                    // if (isprime){
                    //     q.add(n);
                    //     if(Producer.produce(q.peek())){
                    //         q.remove();
                    //     }
                        
                    // }
                    Producer.produce(n, isprime, this);
            }
            // while(!q.isEmpty()){
            //     if(Producer.produce(q.peek(), n%bufferSize)){
            //         q.remove();
            //     }
            // }
        }   
    }
    

