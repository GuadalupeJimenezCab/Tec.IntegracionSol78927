package com.example.demo;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class CalculadoraConfig extends WsConfigurerAdapter{

    @Bean 
    //este método lea el calculadora.xsd y se lo pase al método DefaulWsdl11Definiton
    public XsdSchema calculadoraSchema(){
        return new SimpleXsdSchema(new ClassPathResource("calculadora.xsd"));
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext applicationContext){

        MessageDispatcherServlet servlet= new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }


    @Bean(name = "calculadora")
    /*Recibe el esquema leído, pues un servicio web basado en SOAP nos tiene que devolver un
    WSDL para saber nosotros como utilizarlo*/ 
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema calculadoraSchema){
        DefaultWsdl11Definition wsdl= new DefaultWsdl11Definition();
        wsdl.setPortTypeName("calculadoraPort");
        wsdl.setLocationUri("/ws/calculadora");
        wsdl.setTargetNamespace("http://www.example.org/calculadora");
        wsdl.setSchema(calculadoraSchema);
        return wsdl;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // config.setAllowCredentials(true);
        config.addAllowedOrigin("");
        config.addAllowedHeader("");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
