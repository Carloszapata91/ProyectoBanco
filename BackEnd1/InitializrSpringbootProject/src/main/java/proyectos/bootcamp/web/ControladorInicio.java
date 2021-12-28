/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyectos.bootcamp.domain.Usuario;
import java.util.Date;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import proyectos.bootcamp.dao.UsuarioDao;

/**
 *
 * @author cocot
 */
@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ControladorInicio {

    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioDao en esta clase
    private UsuarioDao usuarioDao;
    
    @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio(Model model){ 
     
     var usuarios = usuarioDao.findAll();
     
     log.info("Ejecutando un controlador Spring MVC");
     
     model.addAttribute("usuarios",usuarios);
     return "index";
     }

}
