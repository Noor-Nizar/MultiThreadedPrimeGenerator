
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
            for (n = start ; n < end; n++){
                boolean isPrime = true;
                for (int j = 2; j <=Math.sqrt(n) ; j++) {
                
                    if(n % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime){
                    q.add(n);
                    if(token){
                        if(hub.add(q.peek(), this)){
                            q.remove();
                        }
                    }
                }
            }
            

            // for (n = start ; n < end; n++) {
            //     // remItr = end - n;
            //     boolean isprime = true;
            //         for (int j = 2; j < n; j++) {
            //                 if (n%j == 0){
            //                     isprime = false;
            //                     break;
            //                       }
            //         }
            //         if (isprime){
            //             q.add(n);
            //             if(token){
            //                 if(hub.add(q.peek(), this)){
            //                     q.remove();
            //                 }
            //             }
            //         }
            // }
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
  
 /*static void bestMethod(int n){
 boolean isPrime = true;
 System.out.print("O(√N) Solution : ");
 for (int i = 2; i <=Math.sqrt(n) ; i++) {
 if(n%i==0) {
 System.out.println("Number " + n +" is not a prime no");
 isPrime = false;
 break;
 }
 }
 if(isPrime)
 System.out.println("Number " + n +" is a prime no");

 //Time Complexity: O(√N)
 }*/

 /* static boolean isPrime(int n)
    {
 
        // Check if number is less than
        // equal to 1
        if (n <= 1)
            return false;
 
        // Check if number is 2
        else if (n == 2)
            return true;
 
        // Check if n is a multiple of 2
        else if (n % 2 == 0)
            return false;
 
        // If not, then just check the odds
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }*/