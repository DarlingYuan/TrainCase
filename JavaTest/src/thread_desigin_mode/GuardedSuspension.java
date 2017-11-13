package thread_desigin_mode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/**
 * @author JasonYuan
 * 被守护而暂停执行(GuardedSuspension)）
 * <Li>存在循环<Li>
 * <Li>存在条件检查<Li>
 * <Li>因为某种原因而等待<Li>
 */
public class GuardedSuspension {

    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        Random random = new Random(3415927L);
        new Thread(new ClientThread(queue, random)).start();
        new Thread(new ServerThread(queue, random)).start();
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
        private final Queue<Request> mQueue = new LinkedList<Request>();

        public RequestQueue(){}
        public synchronized Request getRequest(){
            while(mQueue.peek() == null){
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
                    Thread.sleep(mRandom.nextInt(1000));
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
                    Thread.sleep(mRandom.nextInt(1000));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
