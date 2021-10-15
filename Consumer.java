import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
  private BlockingQueue<Message> queue = null;
  private int id;

  public Consumer(BlockingQueue<Message> q, int n) {
    queue = q;
    id = n;
  }

  public void run() {
    Message msg = null;
    int count = 0;
    do {
      try {
        msg = this.queue.poll(1, TimeUnit.SECONDS); // Get a message from the queue
        if (msg == null || msg.get().equals("stop")) break;
        count++;
        RandomUtils.print("Consumed " + msg.get(), id);
        Thread.sleep(RandomUtils.randomInteger());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (true); // changed to true, break if queue polls "stop" or null

    RandomUtils.print("Messages received: " + count, id);
  }
}
