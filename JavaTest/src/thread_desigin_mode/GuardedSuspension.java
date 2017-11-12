package thread_desigin_mode;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class GuardedSuspension {

    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        Random random = new Random(3415927L);
        new Thread(new ClientThread(queue, random)).start();
        new Thread(new ServerThread(queue, random)).start();
    }
    
    static class Request{
        private String mName;
        public Request(String name){
            this.mName = name;
        }
        public String getName(){
            return mName;
        }
        public String toString(){
            return "[ Request " + mName +" ]";
        }
    }
    
    static class RequestQueue{
        private final Queue<Request> mQueue = new LinkedBlockingQueue<Request>();

        public RequestQueue(){}
        public synchronized Request getRequest(){
            if(mQueue.peek() == null){
                try {
                    System.out.println(Thread.currentThread() + " wait");
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            //return mQueue.poll();
            return mQueue.remove();
        }
        public synchronized void putRequest(Request request){
            this.mQueue.offer(request);
            this.notifyAll();
        }
    }

    static class ClientThread implements Runnable{
        private RequestQueue mQueue;
        private Random mRandom;
        public ClientThread(RequestQueue queue, Random random){
            this.mQueue = queue;
            this.mRandom = random;
        }
        @Override
        public void run() {
            for(int i = 0 ;i < 1000 ;i++){
                Request request = new Request("requst" + i);
                System.out.println(Thread.currentThread() + " putRequest "+request.toString());
                mQueue.putRequest(request);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    static class ServerThread implements Runnable{
        private RequestQueue mQueue;
        private Random mRandom;
        public ServerThread(RequestQueue queue, Random random){
            this.mQueue = queue;
            this.mRandom = random;
        }
        @Override
        public void run() {
            for(int i = 0 ;i < 1000 ;i++){
                Request request = mQueue.getRequest();
                System.out.println(Thread.currentThread() + " getRequest "+request.toString());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
