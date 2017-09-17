package thread.crazy;

class Acount{
    private ThreadLocal<String> account = new ThreadLocal<String>();
    public Acount(String name){
        this.account.set(name);
        System.out.println("-----"+this.account.get());
    }
    public void setAccount(String account){
        this.account.set(account);
    }
    public String getAccount(){
        return this.account.get();
    }
}
/**
 * @author Jason
 * 虽然两条线程共享一个账号，但是账号里的值使用ThreadLocal类型保存的，所以两个线程有各自的账号
 * 副本，而且初始值只能在各自的线程里赋值，主线程中赋予的值不算，所以测试结果显示各线程在i=5之前值
 * 都是null
 */
class ThreadTest extends Thread{
    private Acount acount;
    public ThreadTest(Acount acount) {
        this.acount = acount;
    }
    @Override
    public void run() {
        int i = 0;
        for(;i<10;i++){
            if(i==5){
                this.acount.setAccount(Thread.currentThread().getName());
            }
            System.out.println(this.acount.getAccount()+" count :"+i);
        }
    }
}
public class ThreadLocalTest {
    public static void main(String[] args) {
        Acount acount = new Acount("init data");
        new ThreadTest(acount).start();
        new ThreadTest(acount).start();
    }
}
