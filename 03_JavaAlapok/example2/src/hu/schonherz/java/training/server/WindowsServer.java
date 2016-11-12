package hu.schonherz.java.training.server;

public class WindowsServer extends Server {

    @Override
    public void restart() {
        System.out.println("Windows server is restarting...");
    }

}
