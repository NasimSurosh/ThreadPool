package explination;

public class Tasks implements Runnable {

  private int id;

  public Tasks(int id) {

    this.id = id;
  }

  @Override
  public void run() {

    System.out.println("Task " + id + ": started.... ");

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Task " + id + ": is finished.");
  }

}
