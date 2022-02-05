
package proyectos.bootcamp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import proyectos.bootcamp.entity.Usuario;
import proyectos.bootcamp.service.UsuarioService;


@Controller   
@Slf4j   
public class UsuarioController {
    

    @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio( Usuario datos ){ 
     
          return "loginn";
     }


    @Autowired  
    private UsuarioService usuarrioService;

    @GetMapping("/newUser")
    public String guardarUsuario(Usuario usuario){
        return "crearUsuario";
     }

    @PostMapping("/guardarUsuario")
    public String guardarUsuar (Usuario usuario){
        
        usuarrioService.guardarUsuario(usuario);
        
        log.info("200 OK - Usuario creado con exito ");
        return "201Created_1_1";
     }

    @GetMapping("/loguearse")
    public String loguearse (Usuario datos){
        

        return "home";
     }

}
