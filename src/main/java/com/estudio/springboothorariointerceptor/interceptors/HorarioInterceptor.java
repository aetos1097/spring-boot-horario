package com.estudio.springboothorariointerceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("horario")
public class HorarioInterceptor implements HandlerInterceptor {//interfaz que tendra los metodos

    @Value("${config.horario.apertura}")//usamos los valores que se tiene en el properties
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;

    @Override//metodo amtes de que se envie la solicitud
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //creamos la instancia del calendario de java
        Calendar calendar = Calendar.getInstance();
        //creamos la variable
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        //comparamos con un if que este dentro del rango
        if(hora >= apertura && hora < cierre){
            //creamos un String mutable es decir que ccmabie el objeto cuando queramos
            StringBuilder mensaje = new StringBuilder("Bienvendio al horario de atencion a clientes");//se puede crear string como se quiera sin crear nuevas instancias
            mensaje.append(", atendemos desde las ");
            mensaje.append(apertura);
            mensaje.append("hrs. ");
            mensaje.append("cierre");
            mensaje.append("hrs. ");
            mensaje.append(". Gracias por su visita");
            //pasamos el mensaje a los atributos del request(del receptor a la vista)
            request.setAttribute("mensaje",mensaje.toString());//agrega informacion adicional a la solicitud http en su encabezado  esto se hace durante el request
            return true;
        }
        //ahora si es falso necesitamos redireccionar a una ruta
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }
    //metodo para cuando se envie la solucitud
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String mensaje = (String)request.getAttribute("mensaje");
        //para evitar errores de null
        if(modelAndView != null && handler instanceof HandlerMethod){
            //pasamos el mensaje a la vista con ModelAndView
            modelAndView.addObject("horario",mensaje);
        }

    }
}
