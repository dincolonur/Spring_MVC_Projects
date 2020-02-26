package com.onur;

import com.onur.converter.PDFMessageConverter;
import com.onur.db.DerbyDataSource;
import com.onur.rest.UserPdfViewResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.onur"})
public class PhoneBookConfig extends WebMvcConfigurerAdapter {

  @Bean
  public PlatformTransactionManager txManager() {
    return new DataSourceTransactionManager(getJdbcTemplate().getDataSource());
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setTemplateLoaderPath("/WEB-INF/views/");
    return configurer;
  }

  @Bean
  public ViewResolver freeMarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setSuffix(".ftl");
    resolver.setOrder(1);
    return resolver;
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/jsp/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setOrder(2);
    return viewResolver;
  }

  @Bean
  public DerbyDataSource getDataSource() {
    return new DerbyDataSource();
  }

  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource().dataSource());
  }

  @Bean
  public ObjectMapper jacksonObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper;
  }

  @Bean
  public MappingJackson2HttpMessageConverter jsonConverter() {
    MappingJackson2HttpMessageConverter jacksonConverter =
        new MappingJackson2HttpMessageConverter();
    jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/json")));
    jacksonConverter.setObjectMapper(jacksonObjectMapper());
    return jacksonConverter;
  }

  @Bean
  public ContentNegotiatingViewResolver viewResolver(ContentNegotiationManager cnManager) {

    ContentNegotiatingViewResolver cnResolver = new ContentNegotiatingViewResolver();
    cnResolver.setContentNegotiationManager(cnManager);

    // PDF View Resolver
    UserPdfViewResolver pdfResolver = new UserPdfViewResolver();

    // Add views to list
    List<ViewResolver> resolvers = new ArrayList<>();
    resolvers.add(pdfResolver);

    cnResolver.setViewResolvers(resolvers);
    return cnResolver;
  }

  @Bean
  public PDFMessageConverter pdfMessageConverter(){
      PDFMessageConverter pdfMessageConverter = new PDFMessageConverter();
      return pdfMessageConverter;
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(pdfMessageConverter());
  }
}
