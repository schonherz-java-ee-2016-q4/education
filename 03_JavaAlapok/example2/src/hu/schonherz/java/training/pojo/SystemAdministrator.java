package hu.schonherz.java.training.pojo;

import java.util.List;

import hu.schonherz.java.training.server.Server;

public class SystemAdministrator extends Employee {

    private static final long serialVersionUID = 2244059719140625986L;
    private List<Server> servers;

    public SystemAdministrator() {
        super();
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

}
