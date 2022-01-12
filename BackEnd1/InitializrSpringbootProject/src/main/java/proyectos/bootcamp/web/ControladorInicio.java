/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.web;

import static java.lang.System.console;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
     public String guardar (Usuario usuario,  RedirectAttributes attribute){
        usuario.setFecha_creacion_cuenta("2022-01-11");
        Date fecha=new Date();
        SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        usuario.setFecha_creacion_cuenta(formatoFecha.format(fecha));
        usuarioService.guardar(usuario);
          
        attribute.addFlashAttribute("sucess", "Cliente guardado exitosamente");
        return "redirect:/";
     }

     @GetMapping("/editar/{id_usuario}")
     public String editar(Usuario usuario, Model model, RedirectAttributes attribute){
        usuario = usuarioService.encontrarUsuario(usuario);
        model.addAttribute("usuario", usuario);
       
        attribute.addFlashAttribute("success", "Cliente guardado exitosamente");
        
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
            Date fecha=new Date();
            SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
            cuenta.setFecha_apertura(formatoFecha.format(fecha));
            cuentaService.guardarC(cuenta);
              return "redirect:/";
          
           }else
           log.info("Saldo incorrecto: No puede ser inferior a cero (0)");
                return "redirect:/";
     }

    @GetMapping("/editarCuenta")     //Solicitud GET (metodo de solicitud) para la consulta
    public String verCuentas(Model model){ 
        
        var cuentas = cuentaService.listarCuentas();
        log.info("Coco coco logra ladrar");
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
       
               //if (Double.parseDouble(cuenta.getSaldo())==0){
                   log.info("El saldo es: " + cuenta.getSaldo());
                   cuentaService.eliminarC(cuenta);
             //    }else
                log.info("No permitido: Solo se pueden eliminar cuentas con saldo igual a cero (0)");
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
         
         var cuentas = cuentaService.listarProductosByID(cuenta);
         model.addAttribute("cuentas", cuentas);

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
            log.info(movimientos.getTipo_movimiento());

             if ( Double.parseDouble(movimientos.getCantidad()) > 0){
              double saldo_actual = Double.parseDouble(movimientos.getCantidad()) + Double.parseDouble(movimientos.getSaldo_inicial());
               movimientos.setSaldo_actual(Double.toString(saldo_actual));
                   Date fecha=new Date();
                   SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                   movimientos.setFecha_movimiento(formatoFecha.format(fecha));

                movimientosService.guardarMov(movimientos);
            
              
              return "redirect:/";
          
           }else {
               double saldo_actual = Double.parseDouble(movimientos.getSaldo_inicial())+ Double.parseDouble(movimientos.getCantidad()) ;
               movimientos.setSaldo_actual(Double.toString(saldo_actual));
                  if(saldo_actual>0){
                     movimientosService.guardarMov(movimientos);
                    }else{
                     log.info("Movimiento no permitido: Saldo insuficiente");
                }
               
               }
                return "redirect:/";
     }

    @GetMapping("/estadoCuenta")     //Solicitud GET (metodo de solicitud) para la consulta
    public String estadoCuenta(Usuario usuario, Movimientos movimientos, Cuenta cuenta, Model model){ 
        
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        var movimiento = movimientosService.listarMovimientos(cuenta);
        model.addAttribute("movimiento",movimiento);
        return "estadoCuenta";
    }

    @GetMapping("/estadoCuenta_1")     //Solicitud GET (metodo de solicitud) para la consulta
    public String estadoCuenta_1(@RequestParam Long coco, @RequestParam String tip ,Model model,Movimientos movimientos, Cuenta cuenta){ 
        
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        cuenta.setId_usuario(coco);
        cuenta.setTipo(tip);
        var movimiento = movimientosService.listarMovimientos(cuenta);
        log.info("La cuenta de Coco es : "+ coco);
        model.addAttribute("movimiento",movimiento);
        return "estadoCuenta_1";
    }

    @GetMapping("/estadoCuentaUsuario/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String estadoCuentaUsuario(Cuenta cuenta, Usuario usuario, Movimientos movimientos, Model model){ 
         
        var cuentas = cuentaService.listarProductosByID(cuenta);
        model.addAttribute("cuentas", cuentas);

        usuario = usuarioService.encontrarUsuario(usuario);
        model.addAttribute("usuario", usuario);

        var movimiento = movimientosService.listarMovimientos(cuenta);
        model.addAttribute("movimientos",movimiento);

        movimientos = movimientosService.encontrarMov(movimientos);
        model.addAttribute("movimientos", movimientos);

         return "estadoCuentaUsuario";
    }

}
