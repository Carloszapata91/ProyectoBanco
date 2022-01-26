/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.service.ClienteService;
import proyectos.bootcamp.service.CuentaService;

@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class CuentaController {
    
    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

    @Autowired
    private CuentaService cuentaService;
 
    @GetMapping("/Cuenta")
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

    @GetMapping("/verCuentas")     //Solicitud GET (metodo de solicitud) para la consulta
    public String verCuentas(Model model){ 
        
        var cuentas = cuentaService.listarCuentas();
        log.info("Coco coco logra ladrar");
        model.addAttribute("cuentas",cuentas);

        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "editarCuenta";
    }

    @GetMapping("/crearCuenta/{id_usuario}")
     public String editarCuenta(@RequestParam Long cuentaID, @RequestParam String tip ,Cuenta cuenta, Model model){
        //cuenta = cuentaService.encontrarCuenta(cuenta);
 
        Cuenta cuentaAux = new Cuenta();
        cuentaAux.setId_usuario(cuentaID);
        log.info("CuentaID y tipo: " + cuentaID + tip );
        cuentaAux.setTipo(tip);
        cuenta =cuentaService.EncontrarByIDTipo(cuentaAux);


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
}
