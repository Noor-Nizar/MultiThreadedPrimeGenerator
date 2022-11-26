
import java.util.LinkedList;
import java.util.Queue;


public class Producer extends Thread {

        public Queue<Integer> q = new LinkedList<Integer>();
        // constructor for class
        int start = 0;
        int end;
        // int remItr = 0;
        int threadNumber;
        boolean token = false;
        Hub hub;
        //constructor
        public Producer(int start, int end, int threadNumber, Hub hub) {
            this.start = start;
            this.end = end;
            this.threadNumber = threadNumber;
            this.hub = hub;
            if(threadNumber == 0){
                token = true;
            }
        }


        public void run() {
            int n;
            for (n = start ; n < end; n++) {
                // remItr = end - n;
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
                            if(hub.add(q.peek(), this)){
                                q.remove();
                            }
                        }
                    }
            }
            if(!token){
                // hub.print("thread " + threadNumber + " sleeping");
                hub.sleepen(this);
                // hub.print("thread " + threadNumber + " wokeup");
            }
            while(true){
                if(!q.isEmpty()){
                    if(token){
                        if(hub.add(q.peek(), this)){
                            q.remove();
                        }
                    }
                    else{
                        hub.sleepen(this);
                    }
                } else {
                    if(hub.add(-1, this)){
                        break;
                    }
                }
            }
        //     while(!q.isEmpty()){
        //         // hub.print("Thread"+ threadNumber+" token = " + token);
        //         if(token){
        //             if(hub.add(q.peek(), this)){
        //                 q.remove();
        //             }
        //         }
        //         else{
        //             hub.sleepen(this);
        //         }
        //     }
        //     hub.add(-1, this);
        }   
    }

    /*
    0...20  
    20...40
    40...60
    60...80
    80...100
    */
    

