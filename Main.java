
public class Main {

    static class Worker implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {

                    System.out.println("Worker is running...");

                    Thread.sleep(1000);

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Worker interrupted. Exiting...");
                        break;
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("Worker interrupted. Exiting...");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

        Thread workerThread = new Thread(new Worker());

        System.out.println("Starting Worker...");
        workerThread.start();

        try {
            Thread.sleep(4000);

            System.out.println("Interrupting Worker...");
            workerThread.interrupt();

            workerThread.join();

            System.out.println("Worker has finished.");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
