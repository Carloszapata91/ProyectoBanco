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
import org.springframework.web.bind.annotation.RequestParam;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;
import proyectos.bootcamp.entity.Cliente;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.service.MovimientosService;
import proyectos.bootcamp.service.ClienteService;


@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ProductosController {
    
    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping("/resumenCliente")     //Solicitud GET (metodo de solicitud) para la consulta
    public String resumenUsuario(Cliente usuario, Cuenta cuenta, Model model){ 
        
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "resumenCliente";
    }

    @GetMapping("/productosCliente/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String productosUsuario(Cuenta cuenta, Cliente usuario, Model model){ 
         
         var cuentas = cuentaService.listarProductosByID(cuenta);
         model.addAttribute("cuentas", cuentas);

         usuario = usuarioService.encontrarUsuario(usuario);
         model.addAttribute("usuario", usuario);
         return "productosCliente";
    }
    
    

    @GetMapping("/estadoCuenta")     //Solicitud GET (metodo de solicitud) para la consulta
    public String estadoCuenta(Cliente usuario, Movimientos movimientos, Cuenta cuenta, Model model){ 
        
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

    @GetMapping("/estadoCuentaCliente/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String estadoCuentaUsuario(Cuenta cuenta, Cliente usuario, Movimientos movimientos, Model model){ 
         
        var cuentas = cuentaService.listarProductosByID(cuenta);
        model.addAttribute("cuentas", cuentas);

        usuario = usuarioService.encontrarUsuario(usuario);
        model.addAttribute("usuario", usuario);

        var movimiento = movimientosService.listarMovimientos(cuenta);
        model.addAttribute("movimientos",movimiento);

        movimientos = movimientosService.encontrarMov(movimientos);
        model.addAttribute("movimientos", movimientos);

         return "estadoCuentaCliente";
    }
}
