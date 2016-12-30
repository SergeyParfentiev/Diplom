package project.service;

import project.tables.*;

import java.util.List;

public interface ManagerService {
    List<Role> roleList();

    Role getRoleById(int id);

    List<Services> getServices();

    void addCallback(String name, String phone, String note, long serviceId);

    void addService(String name);

    void addProductType(String name);

    void addProductFirm(String name, String country);

    void addProduct(long productTypeId, long productFirmId, int cost, byte[] photo);

    List<ProductFirm> getAllProductFirms();

    List<ProductType> getAllProductTypes();

    List<Product> getAllProducts();

    long maxPrice();

    long productTypeCount();

    long productFirmCount();

    long allPagesProduct();

    long countPages(int firstNumber, int secondNumber, List<Long> listTypeId, List<Long> listFirmId);

    List<Product> getSampleProductList(int firstNumber, int secondNumber, List<Long> listTypeId, List<Long> listFirmId, long fromId);

    void addOrder(List<Long> productListId, List<Integer> productListCount, String name, String email, String phone, String address);

    List<Callback> callbackList();

    List<Order> orderList();

    List<Services> serviceList();

    List<ProductType> productTypeList();

    List<ProductFirm> productFirmList();

    List<Product> productList();
}
