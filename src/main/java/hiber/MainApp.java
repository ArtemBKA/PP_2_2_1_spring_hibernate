package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<Car> carsList = Arrays.asList(
              new Car(3947, "Nissan Tilda"),
              new Car(7395, "Kia Rio"),
              new Car(9452, "BMW X6"),
              new Car(1946, "Toyota Camry")
      );

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carsList.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carsList.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", carsList.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", carsList.get(3)));

      for (User user : userService.listUsers()) {
         System.out.println(user);
      }

      for (Car car : carsList) {
         System.out.println(userService.getUserByCar(car.getModel(), car.getSeries()));
      }

      context.close();
   }
}
