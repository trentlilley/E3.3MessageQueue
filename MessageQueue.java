import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
  private static int n_ids;

  public static void main(String[] args) {
	  BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
    Set<Producer> producerTasks = new HashSet<Producer>();
    int N = Integer.parseInt(args[0]); // number of consumers
    int M = Integer.parseInt(args[1]); // number of producers

    // create given number of consumers
    for (int i = 0; i < N; i++) {
      new Thread(new Consumer(queue, n_ids++)).start();
    }

    // create given number of producers
    for (int i = 0; i < M; i++) {
      Producer p = new Producer(queue, n_ids++);
      producerTasks.add(p);
      new Thread(p).start();
    }

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // stop the producers
    for (Producer p : producerTasks) {
      p.stop();
    }

  }
}
