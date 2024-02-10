package excercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskProcessor implements Runnable {

  int numbers;

  public TaskProcessor(int numbers) {
    this.numbers = numbers;
  }

  public int calculation() {

    int result = 1;
      for (int i = 1; i <= numbers; i++) {
        result *=i;
      }
      return result;
    }
  

  @Override
  public void run() {

    System.out.println("factorial of " + numbers + " started...");
    try {
      Thread.sleep(500);
      int factorial = calculation();
      System.out.println("Factorial of " + numbers + " is " + factorial);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
    System.out.println("factorial of " + numbers + " finished");

  }

}

public class Main {

  public static void main(String[] args) {

    ExecutorService es = Executors.newFixedThreadPool(1);

    int[] numbers = { 5, 6, 7, 8 };
    for (int number : numbers) {
      TaskProcessor tp = new TaskProcessor(number);
      es.submit(tp);
    }
    es.shutdown();

    try {
      es.awaitTermination(1, TimeUnit.HOURS);
    } catch (InterruptedException e) {
      System.out.println(e);
    }

  }

}
