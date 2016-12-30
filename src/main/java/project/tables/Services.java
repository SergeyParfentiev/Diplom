package project.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Services {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Callback> clients = new ArrayList<>();

    public Services() {
    }

    public Services(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String ruValue) {
        this.name = ruValue;
    }

    public List<Callback> getClients() {
        return Collections.unmodifiableList(clients);
    }
}
