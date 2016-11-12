package hu.schonherz.java.training.server;

public class LinuxServer extends Server {

    @Override
    public void restart() {
        System.out.println("Linux server is restarting...");
    }

}
