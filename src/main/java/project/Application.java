package project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import project.repository.ServicesRepository;
import project.service.ManagerService;
import project.service.UserService;
import project.tables.CustomUser;
import project.tables.Role;
import project.tables.Services;

import java.io.File;
import java.nio.file.Files;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner fillService(final ServicesRepository serviceRepository, final UserService userService,
                                         final ManagerService managerService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                managerService.addService("Продажа фигуруы");
                managerService.addService("Покрасить фигуру");
                managerService.addService("Заменить фигуру");

                managerService.addProductType("Triangle");
                managerService.addProductType("Circle");
                managerService.addProductType("Square");
                managerService.addProductType("Hexagon");
                managerService.addProductType("Star");

                managerService.addProductFirm("GreenPeace", "MyGreen");
                managerService.addProductFirm("RedPeace", "MyRed");
                managerService.addProductFirm("BluePeace", "MyBlue");

                userService.addUser(new CustomUser(Role.ADMIN, "admin", "admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"));
                userService.addUser(new CustomUser(Role.GRAND_ADMIN, "grand", "grand", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"));

                File triangleGreen = new File(getClass().getResource("/shopImages/triangleGreen.png").getPath());
                File triangleBlue = new File(getClass().getResource("/shopImages/triangleBlue.png").getPath());
                File triangleRed = new File(getClass().getResource("/shopImages/triangleRed.png").getPath());

                File circleGreen = new File(getClass().getResource("/shopImages/circleGreen.png").getPath());
                File circleBlue = new File(getClass().getResource("/shopImages/circleBlue.png").getPath());
                File circleRed = new File(getClass().getResource("/shopImages/circleRed.png").getPath());

                File squareGreen = new File(getClass().getResource("/shopImages/squareGreen.png").getPath());
                File squareBlue = new File(getClass().getResource("/shopImages/squareBlue.png").getPath());
                File squareRed = new File(getClass().getResource("/shopImages/squareRed.png").getPath());

                File hexagonGreen = new File(getClass().getResource("/shopImages/hexagonGreen.png").getPath());
                File hexagonBlue = new File(getClass().getResource("/shopImages/hexagonBlue.png").getPath());
                File hexagonRed = new File(getClass().getResource("/shopImages/hexagonRed.png").getPath());

                File starGreen = new File(getClass().getResource("/shopImages/starGreen.png").getPath());
                File starBlue = new File(getClass().getResource("/shopImages/starBlue.png").getPath());
                File starRed = new File(getClass().getResource("/shopImages/starRed.png").getPath());

                managerService.addProduct(1, 1, 100, getBytesFromFile(triangleGreen));
                managerService.addProduct(1, 2, 200, getBytesFromFile(triangleRed));
                managerService.addProduct(1, 3, 130, getBytesFromFile(triangleBlue));

                managerService.addProduct(2, 1, 330, getBytesFromFile(circleGreen));
                managerService.addProduct(2, 2, 40, getBytesFromFile(circleRed));
                managerService.addProduct(2, 3, 100, getBytesFromFile(circleBlue));

                managerService.addProduct(3, 1, 280, getBytesFromFile(squareGreen));
                managerService.addProduct(3, 2, 360, getBytesFromFile(squareRed));
                managerService.addProduct(3, 3, 20, getBytesFromFile(squareBlue));

                managerService.addProduct(4, 1, 42, getBytesFromFile(hexagonGreen));
                managerService.addProduct(4, 2, 710, getBytesFromFile(hexagonRed));
                managerService.addProduct(4, 3, 380, getBytesFromFile(hexagonBlue));

                managerService.addProduct(5, 1, 142, getBytesFromFile(starGreen));
                managerService.addProduct(5, 2, 630, getBytesFromFile(starRed));
                managerService.addProduct(5, 3, 280, getBytesFromFile(starBlue));
            }
        };
    }

    private byte[] getBytesFromFile(File file) {
        byte[] imgData = null;
        try {
            imgData = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgData;
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(80);
        return factory;
    }
}