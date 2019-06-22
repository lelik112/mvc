import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.repository.UserRepository;
import net.tcheltsou.springmvclearning.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");

		UserService userService = context.getBean(UserService.class);
		UserRepository userRepository = context.getBean(UserRepository.class);

		User user = userRepository.getUserByUserName("admin");
		System.out.println(user);
		userService.buyTicket(1L, user);
		user = userRepository.getUserByUserName("admin");
		System.out.println(user);

	}
}
