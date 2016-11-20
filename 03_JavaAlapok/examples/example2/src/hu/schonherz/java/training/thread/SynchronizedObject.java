package hu.schonherz.java.training.thread;

public class SynchronizedObject {

    private int num = 0;

    //public synchronized int getNum() { // Thread2 wait till Thread1 finish its work
                                         // Thread1 and 2 will execute it after each other
    public int getNum() {
        num++;
        
        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return num;
    }

}
