package thread.currenttest.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author Administrator
 * a demo print all file in roots use BlockingQueue;
 * 打印給定目錄下所有文件夾下的所有文件，不包括給定目錄下的文件。
 */
public class BlockingQueueTest {
    class FileCrawler implements Runnable{
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;
        public FileCrawler(BlockingQueue<File> fileQueue,FileFilter fileFilter,
                File root) {
            this.fileQueue = fileQueue;
            this.fileFilter = fileFilter;
            this.root = root;
        }
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // ---------> ?
            }
        }
        private void crawl(File root) throws InterruptedException{
            File[] entries = root.listFiles(fileFilter);
            if(entries != null){
                for(File entry : entries){
                    if(entry.isDirectory()){
                        crawl(entry);
                    }else if(fileFilter.accept(entry)){
                        Thread.sleep(1000);
                        fileQueue.put(entry);
                    }
                }
            }
        }
    }
    
    class Indexer implements Runnable{
        private final BlockingQueue<File> queue;
        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println(queue.take().toString());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // ---------> ?
                }
            }
        }
    }
    public BlockingQueueTest() {
    }
    private void start(){
        BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.getAbsolutePath().contains("FreeMind")){
                    return true;
                }else{
                    return false;
                }
            }
        };
        for(File root : roots.listFiles()){
            new Thread(new FileCrawler(queue, filter, root)).start();
        }
        for(int i = 0; i < N_CONSUMERS; i++){
            new Thread(new Indexer(queue)).start();
        }
    }
    private static final int BOUND = 50;
    private static final int N_CONSUMERS = 20;
    private static final File roots = new File("F:/software/FreeMind");
    public static void main(String[] args) {
        new BlockingQueueTest().start();
    }

}









