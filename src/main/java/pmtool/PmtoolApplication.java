package pmtool;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

@SpringBootApplication
public class PmtoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmtoolApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Hello World!");

//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//
//			EntityManagerFactory emf= Persistence.createEntityManagerFactory("Student_details");
//			EntityManager em=emf.createEntityManager();
//
//			em.getTransaction().begin();
//
//			User s1=new User(1,"user1",User.Role.MANAGER);
//			User s2=new User(1,"user1",User.Role.MANAGER);
//			User s3=new User(1,"user1",User.Role.MANAGER);
//
//			em.persist(s1);
//			em.persist(s2);
//			em.persist(s3);
//
//			em.getTransaction().commit();
//
//			emf.close();
//			em.close();
//		};
		};
	}

	@Bean
	public InitializingBean populateTestData(InMemoryUserRepository repository) {
		return () -> {
			repository.save(new User(1, "user1", User.Role.MANAGER));
			repository.save(new User(2, "user2", User.Role.MANAGER));
			repository.save(new User(3, "user3", User.Role.MANAGER));
		};
	}
}
