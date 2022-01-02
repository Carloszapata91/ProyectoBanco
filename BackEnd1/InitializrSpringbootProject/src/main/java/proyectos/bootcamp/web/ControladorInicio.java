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
import proyectos.bootcamp.domain.Movimientos;
import proyectos.bootcamp.domain.Usuario;
import proyectos.bootcamp.servicio.CuentaService;
import proyectos.bootcamp.servicio.UsuarioService;
import proyectos.bootcamp.servicio.MovimientosService;


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

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping("/")     //Solicitud GET (metodo de solicitud) para la consulta
    public String inicio(Model model){ 
     
     var usuarios = usuarioService.listarUsuarios();
     var cuentas= cuentaService.listarCuentas();
     //var movimientoss = movimientosService.listarMovimientos();

     log.info("Ejecutando un controlador Spring MVC");
     model.addAttribute("usuarios",usuarios);
     model.addAttribute("cuentas",cuentas);
     //model.addAttribute("movimientos",movimientoss);
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
          if ( 0<=  Double.parseDouble(cuenta.getSaldo()) ){
             cuentaService.guardarC(cuenta);
              return "redirect:/";
          
           }else
           log.info("Saldo incorrecto: No puede ser inferior a cero (0)");
                return "redirect:/";
     }

    @GetMapping("/editarCuenta")     //Solicitud GET (metodo de solicitud) para la consulta
    public String verCuentas(Model model){ 
        
        var cuentas = cuentaService.listarCuentas();
        model.addAttribute("cuentas",cuentas);

        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "editarCuenta";
    }

    @GetMapping("/crearCuenta/{id_usuario}")
     public String editarCuenta(Cuenta cuenta, Model model){
        cuenta = cuentaService.encontrarCuenta(cuenta);
        model.addAttribute("cuenta", cuenta);
        return "crearCuenta";
     }

    @GetMapping("/eliminarC/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String eliminarC(Cuenta cuenta){ 
       
                log.info("El saldo es: " + cuenta.getSaldo());
                cuentaService.eliminarC(cuenta);
                log.info("Solo se pueden eliminar cuentas con saldo igual a cero (0)");
              return "redirect:/";
    }

    @GetMapping("/resumenUsuario")     //Solicitud GET (metodo de solicitud) para la consulta
    public String resumenUsuario(Usuario usuario, Cuenta cuenta, Model model){ 
        
         var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "resumenUsuario";
    }

    @GetMapping("/productosUsuario/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String productosUsuario(Cuenta cuenta, Usuario usuario, Model model){ 
         
         cuenta = cuentaService.encontrarCuenta(cuenta);
         model.addAttribute("cuenta", cuenta);

         usuario = usuarioService.encontrarUsuario(usuario);
         model.addAttribute("usuario", usuario);
         return "productosUsuario";
    }
    
    @GetMapping("/crearMovimiento")
    public String crearMovimiento (Movimientos movimientos){
          return "crearMovimiento";
     }

      @PostMapping("/guardarMov")
    public String guardarMov (Movimientos movimientos){
          if ( 0<=  Double.parseDouble(movimientos.getCantidad()) ){
             movimientosService.guardarMov(movimientos);
              return "redirect:/";
          
           }else
           log.info("Saldo incorrecto: No puede ser inferior a cero (0)");
                return "redirect:/";
     }

}
