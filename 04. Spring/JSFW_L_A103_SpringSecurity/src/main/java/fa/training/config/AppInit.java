package fa.training.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] { SpringConfig.class , WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

    @Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter, new DelegatingFilterProxy("springSecurityFilterChain") };
	}
}