package exercise2;

import java.util.concurrent.TimeUnit;

class ProductionLine {
    private Object partLock = new Object();
    private Object productLock = new Object();

    public void producePart(String partName, String workerName) {
        synchronized (partLock) {
            System.out.println(workerName + " produced part: " + partName);
        }
    }

    public void assembleProduct(String productName, String workerName) {
        synchronized (productLock) {
            System.out.println(workerName + " assembled product: " + productName);
        }
    }
}

class Worker implements Runnable {
    private String name;
    private ProductionLine productionLine;

    public Worker(String name, ProductionLine productionLine) {
        this.name = name;
        this.productionLine = productionLine;
    }

    @Override
    public void run() {
        while (true) {
            String partName = "Part-" + name;
            productionLine.producePart(partName, name);

            try {
                TimeUnit.MILLISECONDS.sleep(100); // Simulating time to produce a part
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            String productName = "Product-" + name;
            productionLine.assembleProduct(productName, name);

            try {
                TimeUnit.MILLISECONDS.sleep(100); // Simulating time to assemble a product
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProductionLine productionLine = new ProductionLine();
        int numWorkers = 6;

        Thread[] workers = new Thread[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            workers[i] = new Thread(new Worker("Worker-" + (i + 1), productionLine));
            workers[i].start();
        }

        try {
            // Allow the program to run for 10 seconds
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop the workers
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
}