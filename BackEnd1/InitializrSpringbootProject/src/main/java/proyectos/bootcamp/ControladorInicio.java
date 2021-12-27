/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author cocot
 */
@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue)
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ControladorInicio {
    
    @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio(){
     log.info("Ejecutando un controlador Spring MVC");
     return "index";
     }

}
