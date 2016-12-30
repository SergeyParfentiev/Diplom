package project.tables;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_firm_id")
    private ProductFirm productFirm;

    @Column(nullable = false)
    private int cost;

    @Lob
    @Column(nullable = false, columnDefinition="mediumblob")
    private byte[] image;

    public Product() {
    }

    public Product(ProductType productType, ProductFirm productFirm, int cost, byte[] image) {
        this.productType = productType;
        this.productFirm = productFirm;
        this.cost = cost;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductFirm getProductFirm() {
        return productFirm;
    }

    public void setProductFirm(ProductFirm productFirm) {
        this.productFirm = productFirm;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImage() {
        return Base64.encodeBase64String(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
