/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyectos.bootcamp.domain.Usuario;
import java.util.Date;

/**
 *
 * @author cocot
 */
@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ControladorInicio {
    
    @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio(Model model){
     String mensaje = "Mensaje usando Thymeleaf";

     Usuario usuario= new Usuario();
     usuario.setNombre("Coco");
     usuario.setApellidos("Cocoso");
     usuario.setCorreoElectronico("cocococoso@gmail.com");
     usuario.setTipoIdentificacion("Pasaporte canino");
     usuario.setFechaNacimiento("11/01/2014");

     log.info("Ejecutando un controlador Spring MVC");
     model.addAttribute("mensaje",mensaje);
     model.addAttribute("usuario",usuario);
     return "index";
     }

}
