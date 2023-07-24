package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCarUser(new Car("lada",184));
        user1.getCarUser().setUser(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCarUser(new Car("bmw",265));
        user2.getCarUser().setUser(user2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCarUser(new Car("pw3",612));
        user3.getCarUser().setUser(user3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCarUser(new Car("lola",822));
        user4.getCarUser().setUser(user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        User user5 = userService.findUserByCarModelAndSeries("pw3",612);
        System.out.println(user5.toString());

        context.close();
    }
}
