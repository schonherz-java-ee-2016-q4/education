package hu.schonherz.java.training.server;

import hu.schonherz.java.training.ServerService.WebContainer;

public class LinuxWebServer extends LinuxServer implements WebContainer {

    private Status status;

    public LinuxWebServer(Status status) {
        super();
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status.name();
    }

    @Override
    public void startWebContainer() {
        System.out.println("Web container is starting...");
    }

    @Override
    public void stopWebContainer() {
        System.out.println("Web container is stopping...");
    }

    @Override
    public void restartWebContainer() {
        if (status == Status.RUNNING) {
            stopWebContainer();
        }

        startWebContainer();
    }
}
