package br.com.palpitecerto.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:mysql://localhost:3306/palpite_certo", "root", "mysql");
		flyway.setBaselineOnMigrate(true);
		flyway.migrate();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	
	}

}
