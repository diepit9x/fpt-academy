package fa.training.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class SpringConfig.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "fa.training.repository")
public class HibernateConfig implements ApplicationContextAware {

	/** The application context. */
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	/**
	 * Sets the application context.
	 *
	 * @param applicationContext the new application context
	 * @throws BeansException the beans exception
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * Gets the data source.
	 * 
	 * @return the data source
	 */
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		datasource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=Spring_Security");
		datasource.setUsername("sa");
		datasource.setPassword("123456789");
		return datasource;
	}

	/**
	 * Hibernate properties.
	 *
	 * @return the properties
	 */
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		return hibernateProperties;
	}

	/**
	 * Entity manager factory.
	 *
	 * @return the local container entity manager factory bean
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "fa.training.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(hibernateProperties());
		return entityManagerFactory;
	}

	/**
	 * Entity manager.
	 *
	 * @param entityManagerFactory the entity manager factory
	 * @return the entity manager
	 */
	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * Transaction manager.
	 *
	 * @param entityManagerFactory the entity manager factory
	 * @return the platform transaction manager
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
