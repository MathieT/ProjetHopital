package formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import formation.config.JpaConfig;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		ctx.getBeanFactory().createBean(AppSpring.class).run();
		ctx.close();
	}
}
