package thread;

public class ThreadTest {

	public ThreadTest() {
		
	}
	public static void main(String[] args) {
		for(int i = 0; i <100 ; i++){
		    System.out.println(Thread.currentThread().getName() + " "+i);
			if(i == 20){
			    new TempThread().start();
			    Thread thread = new TempThread();
			    thread.setName("xiancheng-2");
			    thread.start();
			}
		}
	}
	static class TempThread extends Thread{
		@Override
		public void run() {
		    for(int i = 0 ; i < 100 ;i++){
		        System.out.println(getName() + " " +i);
		    }
		}
	}
}
