package fa.training.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "fa.training")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "fa.training.repository")
public class SpringConfig implements WebMvcConfigurer, ApplicationContextAware {
    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	this.applicationContext = applicationContext;
    }
    
    // Khai báo view
    @Bean
    public InternalResourceViewResolver viewResolver() {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/templates/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/assets/**")
		.addResourceLocations("/WEB-INF/templates/assets/");
    }

    // Config hibernate
    // Step 1: config datasource (thông tin kết nối DB)
    @Bean
    public DriverManagerDataSource getDataSource() {
	DriverManagerDataSource datasource = new DriverManagerDataSource();
	datasource.setDriverClassName(
		"com.microsoft.sqlserver.jdbc.SQLServerDriver");
	datasource.setUrl(
		"jdbc:sqlserver://localhost:1433;databaseName=JSFW_L_A103");
	datasource.setUsername("sa");
	datasource.setPassword("123456789");
	return datasource;
    }

    // Hibernate config
    private final Properties hibernateProperties() {
	Properties hibernateProperties = new Properties();
	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	hibernateProperties.setProperty("hibernate.dialect",
		"org.hibernate.dialect.SQLServer2012Dialect");
	hibernateProperties.setProperty("hibernate.show_sql", "true");
	return hibernateProperties;
    }

    // Step 2: config entityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactory.setDataSource(getDataSource());
	entityManagerFactory
		.setPackagesToScan(new String[] { "fa.training.entity" });

	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	entityManagerFactory.setJpaProperties(hibernateProperties());
	return entityManagerFactory;
    }

    // Step 3: Config entity manager
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(
	    EntityManagerFactory entityManagerFactory) {
	return entityManagerFactory.createEntityManager();
    }

    // Step 4: Transaction support
    @Bean
    public PlatformTransactionManager transactionManager(
	    EntityManagerFactory entityManagerFactory) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory);
	return transactionManager;
    }

//    // CORS
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//	registry.addMapping("/**").allowedOrigins("*")
//		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//		.allowedHeaders("*").allowCredentials(true);
//    }
}
