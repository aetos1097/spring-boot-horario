package com.estudio.springboothorariointerceptor.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase controladora para poner interceptores de acceso por horas
 */
@Controller
public class AppController {

    @Value("${config.horario.apertura}")//usamos los valores que se tiene en el properties
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;
    @GetMapping
    public String Index(Model model){//parametro model sirve para poder inyectar al html los valos que se dan en addattribute
        model.addAttribute("titulo","Bienvenido al horario de atencion Index");//fomato map
        return("index");
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model){
        StringBuilder mensaje = new StringBuilder("Cerrado, por favor visitenos desde las: ");
        mensaje.append(apertura);
        mensaje.append(" y las ");
        mensaje.append(cierre);
        mensaje.append(" hrs. Gracias");

        model.addAttribute("titulo","Fuera del horario de atencion");
        model.addAttribute("mensaje",mensaje.toString());//importante mensaje a String porque es un objeto ".toString()"
        return("cerrado");
    }
}
