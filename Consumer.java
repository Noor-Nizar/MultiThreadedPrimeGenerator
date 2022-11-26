import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

public class Consumer extends Thread {
    
    public Queue<Integer> q = new LinkedList<Integer>();
    Hub hub;
    BufferedWriter bw;
    public boolean work = true;
    // constructor for class

    public Consumer(Hub hub) {
        this.hub = hub;
        try{
            bw = new BufferedWriter(new FileWriter("output.txt", true));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void consume() {
        synchronized (Program.hub) {
            q = hub.consume();
            // hub.print(q.toString());
            
            try{
                while(!q.isEmpty()){
                    bw.write(q.remove()+",");
                }
                q.clear();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public void run() {
        while(work){
            consume();

        }
        try{
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
