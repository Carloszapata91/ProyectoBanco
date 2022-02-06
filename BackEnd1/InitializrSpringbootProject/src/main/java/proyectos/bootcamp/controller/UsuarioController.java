
package proyectos.bootcamp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/newUser")
    public String guardarUsuario(Usuario usuario){
        return "crearUsuario";
    }
                                                            //BCryptPasswordEncoder
    @PostMapping("/guardarUsuario")
    public String guardarUsuar (Usuario usuario){
        
        String pass= usuario.getContrasena();
        String passEncrip = passwordEncoder.encode(pass);

        usuario.setContrasena(passEncrip);

        usuarrioService.guardarUsuario(usuario);
        
        log.info("200 OK - Usuario creado con exito ");
        return "201Created_1_1";
    }

    @GetMapping("/user")
    public String buscarUsuar (Usuario usuario){
        
        //usuarrioService.guardarUsuario(usuario);
        
        log.info("200 OK - Usuario encontrado con exito ");
        return "buscarUsuario";
    }

    @PostMapping("/buscarUsuario")
    public String buscarUsuario (Usuario usuario, Model model){
        
        String passwordTemporal = usuario.getContrasena();
        //usuario = usuarrioService.encontrarUsuarrio(usuario);
        Usuario user = usuarrioService.encontrarUsuarrio(usuario);

      if ( !(user == null)){


        String passEncrypted = user.getContrasena();
        
        boolean test = passwordEncoder.matches(passwordTemporal, passEncrypted);

        log.info("El resultado del test es: " + test + " " + passwordTemporal + " " + passEncrypted);


        if (test){
          model.addAttribute(user);
         return "editarUsuario";
         
         }else {return "errorUsuarioContrasena";}
       
      }else {return "errorUsuarioContrasena";}

     }

    @GetMapping("/editUser")
    public String editarUsuario (Usuario usuario){
        
        //usuarrioService.guardarUsuario(usuario);
        
        log.info("200 OK - Usuario creado con exito ");
        return "editarUsuario";
    }


    @PostMapping("/editarUsuario")
    public String editarUsuar (Usuario usuario){
        
        String pass= usuario.getContrasena();
        String passEncrip = passwordEncoder.encode(pass);

        usuario.setContrasena(passEncrip);
  
        usuarrioService.guardarUsuario(usuario);
        
        log.info("200 OK - Usuario editado con exito ");
        return "200OK_1_1";
    }


    @PostMapping("/home")
    public String loguearse (Usuario usuario){
        log.info ("El username es: " + usuario.getUserName() );
       
        String passwordTemporal = usuario.getContrasena();
        //String passDigitada = passwordEncoder.encode(UserNameTemporal);
       // usuario.setContrasena(passDigitada); 

      //  log.info("La contraseña encriptada es: " + passDigitada);
     
        Usuario user =usuarrioService.encontrarUsuarrio(usuario);

     if ( !(user == null)){

        String passEncrypted = user.getContrasena();
        
        boolean test = passwordEncoder.matches(passwordTemporal, passEncrypted);

        log.info("El resultado del test es: " + test + " " + passwordTemporal + " " + passEncrypted);


        if (test){  
          return "200OK";
         }

         else {
         return "errorUsuarioContrasena";   }
         //else
          //{return "200OK";}

      }else{ return "errorUsuarioContrasena"; }
        
    }

}
