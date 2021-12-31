/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.web;

import static java.lang.System.console;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import proyectos.bootcamp.dao.UsuarioDao;
import proyectos.bootcamp.domain.Cuenta;
import proyectos.bootcamp.domain.Usuario;
import proyectos.bootcamp.servicio.CuentaService;
import proyectos.bootcamp.servicio.UsuarioService;


/**
 *
 * @author cocot
 */
@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ControladorInicio {

    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

        @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio(Model model){ 
     
     var usuarios = usuarioService.listarUsuarios();
    // var cuentas= cuentaService.listarCuentas();

     log.info("Ejecutando un controlador Spring MVC");
     model.addAttribute("usuarios",usuarios);
    // model.addAttribute("cuentas",cuentas);
       return "index";
     }

     @GetMapping("/crearUsuario")
     public String crearUsuario(Usuario usuario){
         return "modificar";
     }

     @PostMapping("/guardar")
     public String guardar (Usuario usuario){
          usuarioService.guardar(usuario);
           return "redirect:/";
     }

     @GetMapping("/editar/{id_usuario}")
     public String editar(Usuario usuario, Model model){
        usuario = usuarioService.encontrarUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "modificar";
     }

    @GetMapping("/verUsuarios")     //Solicitud GET (metodo de solicitud) para la consulta
    public String verUsuarios(Model model){ 
        
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "verUsuarios";
    }

    @GetMapping("/eliminar/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String eliminar(Usuario usuario){ 
        usuarioService.eliminar(usuario);
        return "redirect:/";
    }

     @GetMapping("/crearCuenta")
     public String crearCuenta(Cuenta cuenta){
         return "crearCuenta";
     }
     
  //  @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface CuentaService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (cuentaDao)
 //   private CuentaService cuentaService;

     @PostMapping("/guardarC")
    public String guardarC (Cuenta cuenta){
          log.info("Coco coco coco coco coco coco " + cuenta.getId_usuario() + " Tipo: " + cuenta.getTipo()) ;
          
          cuentaService.guardarC(cuenta);
           return "redirect:/";
     }

    
}
