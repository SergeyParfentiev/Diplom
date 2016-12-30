package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.repository.*;
import project.tables.*;

import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService {

    private int productsPerPage = 6;

    @Autowired
    private ServicesRepository service;

    @Autowired
    private CallbackRepository callback;

    @Autowired
    private ProductTypeRepository productType;

    @Autowired
    private ProductFirmRepository productFirm;

    @Autowired
    private ProductRepository product;

    @Autowired
    private OrderRepository order;

    @Override
    public List<Role> roleList() {
        return Arrays.asList(Role.values());
    }

    @Override
    public Role getRoleById(int id) {
        return Role.getRole(id);
    }

    @Override
    @Transactional()
    public List<Services> getServices() {
        return service.findAll();
    }

    @Override
    @Transactional
    public void addCallback(String name, String phone, String note, long serviceId) {
        Services serviceTable = service.findOne(serviceId);
        callback.save(new Callback(name, phone, note, serviceTable));
    }

    @Override
    @Transactional
    public void addProductType(String name) {
            productType.save(new ProductType(name));
    }

    @Override
    @Transactional
    public void addProductFirm(String name, String country) {
        productFirm.save(new ProductFirm(name, country));
    }

    @Override
    @Transactional
    public void addProduct(long productTypeId, long productFirmId, int cost, byte[] photo) {
        ProductType type = productType.findOne(productTypeId);
        ProductFirm firm = productFirm.findOne(productFirmId);

        product.save(new Product(type, firm, cost, photo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductType> getAllProductTypes() {
        return productType.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductFirm> getAllProductFirms() {
        return productFirm.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return product.allProducts(new PageRequest(0, productsPerPage));
    }

    @Override
    @Transactional(readOnly = true)
    public long maxPrice() {
        return product.maxPrice();
    }

    @Override
    public long productTypeCount() {
        return productType.count();
    }

    @Override
    public long productFirmCount() {
        return productFirm.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long allPagesProduct() {
        long count = product.count();
        long pages = count / productsPerPage;
        if ((count % productsPerPage) != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public long countPages(int firstNumber, int secondNumber, List<Long> listTypeId, List<Long> listFirmId){
        long count = product.countPages(firstNumber, secondNumber, listTypeId, listFirmId);
        long pages = count / productsPerPage;
        if ((count % productsPerPage) != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getSampleProductList(int firstNumber, int secondNumber, List<Long> listTypeId, List<Long> listFirmId, long fromId) {
        return product.productList(firstNumber, secondNumber, listTypeId, listFirmId, new PageRequest((int)fromId, productsPerPage));
    }

    @Override
    @Transactional
    public void addOrder(List<Long> productListId, List<Integer> productListCount, String name, String email, String phone, String address) {
        Order order = new Order(name, email, phone, address);
        Iterator id = productListId.iterator();
        for (Integer count : productListCount) {
            id.hasNext();
            order.addProductOrder(new ProductOrder((Long) id.next(), count));
        }
        this.order.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Callback> callbackList() {
        return callback.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> orderList() {
        return order.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Services> serviceList() {
        return service.findAll();
    }

    @Override
    @Transactional
    public void addService(String name) {
        service.save(new Services(name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductType> productTypeList() {
        return productType.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductFirm> productFirmList() {
        return productFirm.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> productList() {
        return product.findAll();
    }
}
