/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.service.MovimientosService;
import proyectos.bootcamp.service.ClienteService;



@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class InicioController {

    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

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



    


}
