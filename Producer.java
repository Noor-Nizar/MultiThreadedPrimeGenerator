
/**
 * Producer
 */
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Producer {
    static int counter = 0;
    static int max = 1;
    public static boolean produce(int val, boolean prime, MyThread mt) {
        // while (Program.bufsize == counter)
            ;
        while (max != val - 1)
            ;
        synchronized (mt) {
            if (prime) {
                Program.a.add(val);
            }
            max++;
            // counter++;
        }
        print();
        return true;
    }
}