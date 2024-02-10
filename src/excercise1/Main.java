package excercise1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class PrintTask implements Runnable {

  String document;

  public PrintTask(String document) {
    this.document = document;
  }

  @Override
  public void run() {

    System.out.println("Printing started...." + document);
    try {
      Thread.sleep(1000);
      System.out.println("Printing " + document);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
    System.out.println("Printing finished." + document);

  }

}

public class Main {

  public static void main(String[] args) {

    ExecutorService es = Executors.newFixedThreadPool(3);

    String[] documents = { "Document", "Document1", "Document2" };

    for (String document : documents) {
      PrintTask pt = new PrintTask(document);
      es.submit(pt);
    }

    es.shutdown();

    try {
      es.awaitTermination(30, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.getStackTrace();
    }

  }

}
