import net.tcheltsou.springmvclearning.entity.UserAccount;
import net.tcheltsou.springmvclearning.repository.DaoConfig;
import net.tcheltsou.springmvclearning.repository.EventRepository;
import net.tcheltsou.springmvclearning.repository.UserAccountRepository;
import net.tcheltsou.springmvclearning.service.UserAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");

		UserAccountService userAccountService = context.getBean(UserAccountService.class);
		UserAccountRepository userRepository = context.getBean(UserAccountRepository.class);

		UserAccount user = userRepository.getUserByUserName("admin");
		System.out.println(user);
		userAccountService.buyTicket(1L, user);
		user = userRepository.getUserByUserName("admin");
		System.out.println(user);

	}
}
