package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.ManagerService;
import project.tables.Product;
import project.tables.ProductFirm;
import project.tables.ProductType;
import project.tables.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class PublicController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/")
    public String onIndex() {
        return "redirect:/menu";
    }

    @RequestMapping(value = "/chooseProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,
                        @RequestParam(value = "checkboxProductTypeId", required = false) List<Long> productTypeId,
                        @RequestParam(value = "checkboxProductFirmId", required = false) List<Long> productFirmIdList,
                        @RequestParam(value = "priceFrom", required = false) String priceFrom,
                        @RequestParam(value = "priceTo", required = false) String priceTo,
                        @RequestParam(value = "page", required = false) String page) {

        List<Long> typeIdList;
        List<Long> firmIdList;
        long productTypeCount = managerService.productTypeCount();
        long productFirmCount = managerService.productFirmCount();

        if (productFirmIdList != null) {
            firmIdList = productFirmIdList;
        } else {
            firmIdList = new ArrayList<>();
            for (long i = 1; i <= productFirmCount; i++) {
                firmIdList.add(i);
            }
        }

        if (productTypeId != null) {
            typeIdList = productTypeId;
        } else {
            typeIdList = new ArrayList<>();
            for (long i = 1; i <= productTypeCount; i++) {
                typeIdList.add(i);
            }
        }

        if (page == null) {
            page = "1";
            long totalPages = managerService.countPages(Integer.valueOf(priceFrom), Integer.valueOf(priceTo), typeIdList, firmIdList);
            model.addAttribute("totalPages", totalPages);
        }
        int currentPage = Integer.parseInt(page);

        List<Product> productList = managerService.getSampleProductList(Integer.valueOf(priceFrom), Integer.valueOf(priceTo), typeIdList, firmIdList, --currentPage);

        model.addAttribute("productList", productList);
        return "/public/products";
    }

    @RequestMapping("/shop")
    public String shop(Model model) {
        List<ProductType> productTypes = managerService.getAllProductTypes();
        List<ProductFirm> productFirms = managerService.getAllProductFirms();
        long maxPrice = managerService.maxPrice();

        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("productFirms", productFirms);
        return "/public/shop";
    }

    @RequestMapping("/productsList")
    public String productsList(Model model) {
        List<Product> productList = managerService.getAllProducts();
        long totalPages = managerService.allPagesProduct();

        model.addAttribute("productList", productList);
        model.addAttribute("totalPages", totalPages);
        return "/public/products";
    }

    @RequestMapping("/menu")
    public String menu(Model model) {
        List<Services> services = managerService.getServices();
        model.addAttribute("services", services);
        return "/public/menu";
    }

    @RequestMapping("/home")
    public String home() {
        return "/public/home";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "/public/contact";
    }

    @RequestMapping(value = "/addCallback", method = RequestMethod.POST)
    public ResponseEntity<String> addCallback(String name, String phone, String note,
                                              @RequestParam("service") long serviceId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        try {
            managerService.addCallback(name, phone, note, serviceId);
            return new ResponseEntity<>("true", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage()); //check out the error
            return new ResponseEntity<>("false", headers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<String> addOrder(String name, String email, String phone, String address,
                                           @RequestParam(value = "id", required = false) List<Long> productListId,
                                           @RequestParam(value = "count", required = false) List<Integer> productListCount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        managerService.addOrder(productListId, productListCount, name, email, phone, address);
        return new ResponseEntity<>("true", headers, HttpStatus.OK);
    }
}
