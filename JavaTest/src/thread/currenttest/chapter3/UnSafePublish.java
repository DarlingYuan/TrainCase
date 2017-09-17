package thread.currenttest.chapter3;

public class UnSafePublish implements Runnable{
    private Holder holder;// = new Holder(100);
    class Holder{
        private int n;
        public Holder(int n){
            this.n = n;
        }
        public void assertSanity(){
            if(n!=n){
                throw new AssertionError("This statement is false");
            }
        }
    }
    public UnSafePublish(int n) {
        System.out.println("current Thread :" + Thread.currentThread().getName());
        holder = new Holder(n);
    }
    public Holder getHolder(){
        return holder;
    }
    public void setHodler(Holder holder){
        this.holder = holder;
    }
    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish(50);
        new Thread(unSafePublish).start();
        Holder holder = unSafePublish.getHolder();
        unSafePublish.setHodler(holder);
        new Thread(unSafePublish).start();
        new Thread(unSafePublish).start();
        new Thread(unSafePublish).start();
        new Thread(unSafePublish).start();
    }
    @Override
    public void run() {
        holder = new Holder(100);
        System.out.println("current Thread :" + Thread.currentThread().getName());
        holder.assertSanity();
    }

}
