package project.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "product_types")
public class ProductType {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public ProductType() {
    }

    public ProductType(String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
