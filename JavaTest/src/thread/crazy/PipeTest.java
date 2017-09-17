package thread.crazy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.nio.CharBuffer;
class MyPipedWriterThread extends Thread{
    private PipedWriter mPipedWriter;
    public MyPipedWriterThread(PipedWriter pipedWriter) {
        this.mPipedWriter = pipedWriter;
    }
    @Override
    public void run() {
        super.run();
        String[] writer = {
            "never give up",
            "I'am the best",
            "you are so good",
            "try your best"
        };
        int i = 0;
            try {
                for(;i<100;i++){
                mPipedWriter.write(writer[i%4]+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(mPipedWriter!=null){
                    try {
                        mPipedWriter.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        
    }
}
class MyPipeReaderThread extends Thread{
    private PipedReader mPipedReader;
    private BufferedReader mBufferedReader;
    public MyPipeReaderThread(PipedReader pipedReader) {
        this.mPipedReader = pipedReader;
        this.mBufferedReader = new BufferedReader(pipedReader);
    }
    @Override
    public void run() {
        super.run();
        CharBuffer cb = CharBuffer.allocate(2048);
        //String buffer = "";
        try {
           while(mBufferedReader.read(cb)>0){
               cb.flip();
               while(cb.hasRemaining()){
                   char ch = cb.get();
                   System.out.print(ch);
               }
               cb.clear();
            }
           /** while((buffer = mBufferedReader.readLine())!=null){
                System.out.println(buffer);
            }*/
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(mBufferedReader!=null){
                try {
                    mBufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
public class PipeTest {
    public static void main(String[] args) {
        PipedWriter mPipedWriter = new PipedWriter();
        PipedReader mPipedReader = new PipedReader();
        try {
            mPipedReader.connect(mPipedWriter);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new MyPipedWriterThread(mPipedWriter).start();
        new MyPipeReaderThread(mPipedReader).start();
    }
}
