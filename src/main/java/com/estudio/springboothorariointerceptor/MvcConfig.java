package com.estudio.springboothorariointerceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Clase para registrar nuestros interceptores
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //primer se debe inyectar
    @Autowired
    @Qualifier("horario")
    private HandlerInterceptor horario;// tipo generico de nuestra clase HorarioInterceptors es decir la interfaz

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(horario).excludePathPatterns("/cerrado");//sin esto se aplica a los controladores en general
        //esto quiere decir que se redigira y no ira a ninguna parte
    }
}
