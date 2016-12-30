package project.tables;

import javax.persistence.*;

@Entity
@Table(name = "product_orders")
public class ProductOrder {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private int productCount;

    public ProductOrder() {
    }

    public ProductOrder(long productId, int productCount) {
        this.productId = productId;
        this.productCount = productCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
