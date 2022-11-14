
/**
 * Producer
 */
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Producer {
    static int counter = 0;
    static int scounter = 0;
    public static boolean produce(int val, int indx, int start) {
        // // while (Program.bufsize == counter)
        //     ;
        // while (max != val - 1)
        //     ;
        // synchronized (mt) {
        //     if (prime) {
        //         Program.a.add(val);
        //     }
        //     max++;
        //     // counter++;
        // }
        // print();
        if(val == 0){counter++;
        } else{
        Program.a[indx-counter-scounter*Program.nThreads*start]=val;
        }
        return true;
    }
}