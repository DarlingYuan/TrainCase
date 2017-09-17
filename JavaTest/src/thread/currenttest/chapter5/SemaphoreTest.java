package thread.currenttest.chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    class BoundedHashSet<T>{
        private final Set<T> set;
        private final Semaphore sem;
        public BoundedHashSet(int bound) {
            this.set = Collections.synchronizedSet(new HashSet<T>());
            this.sem = new Semaphore(bound);
        }
        public boolean add(T o) throws InterruptedException{
            sem.acquire();
            boolean wasAdded = false;
            try{
                wasAdded = set.add(o);
                return wasAdded;
            }
            finally{
                if(!wasAdded){
                    sem.release();
                }
            }
        }
        public boolean remove(Object o){
            boolean wasRemoved = set.remove(o);
            if(wasRemoved){
                sem.release();
            }
            return wasRemoved;
        }
    }
    class BoundedHashSetAdd<String> implements Runnable{
        BoundedHashSet<String> mBoundedHashSet;
        public BoundedHashSetAdd(BoundedHashSet<String> boundedHashSet) {
            this.mBoundedHashSet = boundedHashSet;
        }
        @Override
        public void run() {
            try {
                String a = (String) Integer.toString((new Random()).nextInt(10));
                mBoundedHashSet.add(a);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public SemaphoreTest() {
        BoundedHashSet boundedHashSet = new BoundedHashSet<String>(10);
        
    }

}
