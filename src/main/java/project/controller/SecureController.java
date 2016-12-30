package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.service.ManagerService;
import project.service.UserService;
import project.tables.*;

import java.io.IOException;
import java.util.List;

@Controller
public class SecureController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String loginPage() {
        return "/secure/login";
    }

    @RequestMapping("/admin")
    public String menu() {
        return "/secure/admin";
    }

    @RequestMapping("/showCallbacks")
    public String showCallbacks(Model model) {
        List<Callback> callbackList = managerService.callbackList();

        model.addAttribute("callbackList", callbackList);
        return "/secure/callbacks";
    }

    @RequestMapping("/showOrders")
    public String showOrders(Model model) {
        List<Order> orderList = managerService.orderList();

        model.addAttribute("orderList", orderList);
        return "/secure/orders";
    }

    @RequestMapping("/showServices")
    public String showServices(Model model) {
        List<Services> serviceList = managerService.serviceList();

        model.addAttribute("serviceList", serviceList);
        return "/secure/services";
    }

    @RequestMapping("/addService")
    public String addService(Model model,
                             @RequestParam(value = "newService", required = false) String newService) {
        List<Services> serviceList = managerService.serviceList();

        if (newService != null && !"".equals(newService.replaceAll(" ", ""))) {
            serviceList.add(new Services(newService));
            managerService.addService(newService);
        }

        model.addAttribute("serviceList", serviceList);
        return "/secure/services";
    }

    @RequestMapping("/showProductTypes")
    public String showProductTypes(Model model) {
        List<ProductType> productTypeList = managerService.productTypeList();

        model.addAttribute("productTypeList", productTypeList);
        return "/secure/productTypes";
    }

    @RequestMapping("/addProductType")
    public String addProductType(Model model,
                                 @RequestParam(value = "productType", required = false) String productType) {
        List<ProductType> productTypeList = managerService.productTypeList();

        if (!"".equals(productType.replaceAll(" ", ""))) {
            productTypeList.add(new ProductType(productType));
            managerService.addProductType(productType);
        }

        model.addAttribute("productTypeList", productTypeList);
        return "/secure/productTypes";
    }

    @RequestMapping("/showProductFirms")
    public String showProductFirms(Model model) {
        List<ProductFirm> productFirmList = managerService.productFirmList();

        model.addAttribute("productFirmList", productFirmList);
        return "/secure/productFirms";
    }

    @RequestMapping("/addProductFirm")
    public String addProductFirm(Model model,
                                 @RequestParam(value = "productFirmName", required = false) String productFirmName,
                                 @RequestParam(value = "productFirmCountry", required = false) String productFirmCountry) {
        List<ProductFirm> productFirmList = managerService.productFirmList();

        if (!"".equals(productFirmName.replaceAll(" ", "")) && !"".equals(productFirmCountry.replaceAll(" ", ""))) {
            productFirmList.add(new ProductFirm(productFirmName, productFirmCountry));
            managerService.addProductFirm(productFirmName, productFirmCountry);
        }

        model.addAttribute("productFirmList", productFirmList);
        return "/secure/productFirms";
    }

    @RequestMapping("/showProducts")
    public String showProducts(Model model) {
        List<Product> productList = managerService.productList();
        List<ProductFirm> productFirmList = managerService.productFirmList();
        List<ProductType> productTypeList = managerService.productTypeList();

        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productFirmList", productFirmList);
        model.addAttribute("productList", productList);
        return "/secure/products";
    }

    @RequestMapping("/addProduct")
    public ResponseEntity addProduct(
            @RequestParam(value = "productTypeId", required = false) String productTypeId,
            @RequestParam(value = "productFirmId", required = false) String productFirmId,
            @RequestParam(value = "productCost", required = false) String productCost,
            @RequestParam(value = "productPhoto", required = false) MultipartFile productPhoto) {

        System.out.println("productTypeId: " + productTypeId + " productFirmId: " + productFirmId + " productCost: " +
                productCost + " productPhoto: " + productPhoto);
        if (productTypeId != null && productFirmId != null && !"".equals(productCost) && productPhoto != null) {

            byte[] photo = null;
            try {
                photo = productPhoto.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            managerService.addProduct(Long.valueOf(productTypeId), Long.valueOf(productFirmId), Integer.valueOf(productCost), photo);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping("/showAdmins")
    public String admins(Model model) {
        List<CustomUser> customUserList = userService.customUserList();
        List<Role> roleList = managerService.roleList();

        model.addAttribute("roleList", roleList);
        model.addAttribute("customUserList", customUserList);
        return "/secure/admins";
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(@RequestParam(value = "adminRole", required = false) String adminRole,
                           @RequestParam(value = "adminName", required = false) String adminName,
                           @RequestParam(value = "adminLogin", required = false) String adminLogin,
                           @RequestParam(value = "adminPassword", required = false) String adminPassword) {

        if (adminRole != null && !"".equals(adminName) && !"".equals(adminLogin) && !"".equals(adminPassword)
                && !userService.existsByLogin(adminLogin)) {
            Role role = managerService.getRoleById(Integer.valueOf(adminRole));

            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            String passHash = encoder.encodePassword(adminPassword, null);

            userService.addUser(new CustomUser(role, adminName, adminLogin, passHash));
        }

        return "redirect:/showAdmins";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
        Role role = dbUser.getRole();
        model.addAttribute("role", role);

        return "/secure/accessDenied";
    }
}