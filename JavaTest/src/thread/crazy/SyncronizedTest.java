package thread.crazy;
/**
 * @author Administrator
 * 对象锁 （this）（object wait notify notifyall）
 * 类.class锁 static synconized
 * 任意对象作为锁  同步代码块
 * 
 * 只要锁被持有，其它线程访问相同锁(锁一定相同)的方法或field(方法或field可以不同)都要等解锁。
 *
 */
class syncronizedA{
    private int a;
    private boolean flag;
    public void setFlag(boolean f){
        this.flag = f;
    }
    public synchronized void fun1(){
        System.out.println("fun1 " + a++);
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public synchronized void fun2(){
        System.out.println("fun2 " + a++);
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
public class SyncronizedTest {
    public static void main(String[] args) {
        syncronizedA c1 = new syncronizedA();
        c1.fun1();
        c1.fun1();
    }
}
