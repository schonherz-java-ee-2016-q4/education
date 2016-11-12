package hu.schonherz.java.training.thread;

public class SynchronizationTest extends Thread {

    private int number;
    private static SynchronizedObject synchronizedObject = new SynchronizedObject();

    public SynchronizationTest(int number) {
        super();
        this.number = number;
    }

    @Override
    public void run() {
        int i = 0;

        // Only one thread can run this block at a time
        // In this case Thread2 won't run till Thread1 finished execution of the "while" loop
        synchronized (synchronizedObject) {
            while (i < 5) {
                i++;
                System.out.println(number + " - " + synchronizedObject.getNum());
            }
        }
    }

        @Override
        public synchronized void start() {
            System.out.println(number + ". started");
            super.start(); 
        }

}
