
package proyectos.bootcamp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyectos.bootcamp.entity.Cliente;
import proyectos.bootcamp.entity.Usuario;
import proyectos.bootcamp.service.UsuarioService;


@Controller   
@Slf4j   
public class UsuarioController {
    
    @Autowired  
    private UsuarioService usuarrioService;

    @GetMapping("/usuarios")
    public String guardarUsuario(Usuario usuarrio){
        return "modificar";
     }

    @PostMapping("/guardarUsuario")
    public String guardarUsuar (Usuario usuarrio){
        
        usuarrioService.guardarUsuario(usuarrio);
          
    

         log.info("200 OK - Cliente creado con exito ");
        return "201Created_1";
     }

}
