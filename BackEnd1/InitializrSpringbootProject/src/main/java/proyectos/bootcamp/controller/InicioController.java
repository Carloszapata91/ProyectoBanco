/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.controller;

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
import proyectos.bootcamp.repository.UsuarioDao;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;
import proyectos.bootcamp.entity.Transferencia;
import proyectos.bootcamp.entity.Usuario;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.service.UsuarioService;
import proyectos.bootcamp.service.MovimientosService;


/**
 *
 * @author cocot
 */
@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class InicioController {

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
        try{
        usuarioService.eliminar(usuario);
        return "redirect:/";
        }catch (Exception e) {
          log.info("No es posible eliminar a este cliente, porque tiene productos activos");
          return "redirect:/";
        }
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
    public String guardarMov (Movimientos movimientos, Cuenta cuenta){
            log.info(movimientos.getTipo_movimiento());
        try{    
            Cuenta cuentaAuxiliar = new Cuenta(); 
            cuentaAuxiliar=cuentaService.EncontrarByIDTipo(cuenta);
            
         
             if (!movimientos.getTipo_movimiento().equals("Retiro") && !movimientos.getTipo_movimiento().equals("0") ){
                double saldo_actual = Double.parseDouble(movimientos.getCantidad()) + Double.parseDouble(cuentaAuxiliar.getSaldo());
                movimientos.setSaldo_inicial(cuentaAuxiliar.getSaldo());
                movimientos.setSaldo_actual(Double.toString(saldo_actual));
                cuentaAuxiliar.setSaldo(Double.toString(saldo_actual));
                
                   Date fecha=new Date();
                   SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                  movimientos.setFecha_movimiento(formatoFecha.format(fecha));

                cuentaService.guardarC(cuentaAuxiliar);
                movimientosService.guardarMov(movimientos);
            
               return "redirect:/";
          
           }else {
               
                 log.info("Hola: " + Double.parseDouble(cuentaAuxiliar.getSaldo()));
               double saldo_actual =  Double.parseDouble(cuentaAuxiliar.getSaldo()) - ((1.004)*Double.parseDouble(movimientos.getCantidad()));
               movimientos.setSaldo_actual(Double.toString(saldo_actual));
                        
                  if( (saldo_actual>=0 && cuentaAuxiliar.getEstado().equals("Activa") && !movimientos.getTipo_movimiento().equals("0")) || (cuentaAuxiliar.getTipo().equals("Corriente") && saldo_actual>=-2000000 && cuentaAuxiliar.getEstado().equals("Activa") && !movimientos.getTipo_movimiento().equals("0"))){
                    
                    movimientos.setSaldo_inicial(cuentaAuxiliar.getSaldo());
                    movimientos.setSaldo_actual(Double.toString(saldo_actual));
                    cuentaAuxiliar.setSaldo(Double.toString(saldo_actual));
                
                    Date fecha=new Date();
                    SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                    movimientos.setFecha_movimiento(formatoFecha.format(fecha));

                    cuentaService.guardarC(cuentaAuxiliar);
                    log.info("Hola Cocococococo:" + Double.toString(saldo_actual));
                    movimientosService.guardarMov(movimientos);

                    }else{
                     log.info("Movimiento no permitido: Saldo insuficiente");
                }
               
               }
                return "redirect:/";

        }catch (Exception e) {
          log.info("No es posible hacer esta operacion: cuenta inexistente");
          return "redirect:/";
        }
        
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

    @GetMapping("/crearTransferencia")
    public String crearTransferencia (Transferencia transferencia){
          return "crearTransferencia";
     }

         @PostMapping("/guardarTransferencia")
    public String guardarTransferencia (Movimientos movimientos, Cuenta cuenta, Transferencia transferencia){
        try{ 
          log.info("Cuenta de origen del Cococococococcococococo: " + transferencia.getTipoCuentaOrigen());
           
            Cuenta cuentaAux = new Cuenta(); 
            cuentaAux.setId_usuario(transferencia.getCuentaOrigen()); 
            cuentaAux.setTipo(transferencia.getTipoCuentaOrigen());
            cuentaAux = cuentaService.EncontrarByIDTipo(cuentaAux);

            Cuenta cuentaOrigen = new Cuenta(); 
            cuentaOrigen=cuentaService.EncontrarByIDTipo(cuentaAux);

            Cuenta cuentaDestino = new Cuenta(); 
            Cuenta cuentaAux2 = new Cuenta();
            cuentaAux2.setId_usuario(transferencia.getCuentaDestino()); 
            cuentaAux2.setTipo(transferencia.getTipoCuentaDestino());
            cuentaDestino=cuentaService.EncontrarByIDTipo(cuentaAux2);
 
            log.info("Aqui va todo bien bien bien: " + transferencia.getTipoCuentaDestino());

            if (cuentaOrigen.getEstado().equals("Activa")  && (!cuentaDestino.getEstado().equals("Inactiva")) && (!cuentaDestino.getEstado().equals("Cancelada")) ){
                    
                 if ( (cuentaOrigen.getTipo().equals("Corriente")&& (Double.parseDouble(cuentaOrigen.getSaldo())- (1.004*Double.parseDouble(transferencia.getCantidad())))>= (-2000000)) || ( cuentaOrigen.getTipo().equals("Ahorros")&& (Double.parseDouble(cuentaOrigen.getSaldo())- (1.001*Double.parseDouble(transferencia.getCantidad())))>=0)  ) { 
                    //Cuenta origen
                      log.info("Aqui va todo bien bien bien");
                    double saldoNuevoOrigen = Double.parseDouble(cuentaOrigen.getSaldo()) - (1.004*Double.parseDouble(transferencia.getCantidad()));
                     
                    movimientos.setSaldo_inicial(cuentaOrigen.getSaldo());
                    movimientos.setSaldo_actual(Double.toString(saldoNuevoOrigen));
                    movimientos.setId_usuario(cuentaOrigen.getId_usuario());
                    movimientos.setDescripcion("Debito por transferencia");
                    movimientos.setTipo_movimiento("Transferencia");
                    movimientos.setCantidad(transferencia.getCantidad());
                    
                    Date fecha=new Date();
                    SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                    movimientos.setFecha_movimiento(formatoFecha.format(fecha));
   
                    movimientos.setTipo(transferencia.getTipoCuentaOrigen());

                    movimientosService.guardarMov(movimientos);

                    cuentaOrigen.setSaldo(Double.toString(saldoNuevoOrigen)); 
                    cuentaService.guardarC(cuentaOrigen);

                    //Cuenta destino
                    double saldoNuevoDestino = Double.parseDouble(cuentaDestino.getSaldo()) + Double.parseDouble(transferencia.getCantidad());
                    
                    Movimientos movimientoT = new Movimientos();

                    movimientoT.setSaldo_inicial(cuentaDestino.getSaldo());
                    movimientoT.setSaldo_actual(Double.toString(saldoNuevoDestino));
                    movimientoT.setId_usuario(cuentaDestino.getId_usuario());
                    movimientoT.setDescripcion("Credito por transferencia");
                    movimientoT.setTipo_movimiento("Transferencia");
                    movimientoT.setCantidad(transferencia.getCantidad());
                    
                    Date fecha2=new Date();
                    SimpleDateFormat  formatoFecha2 = new SimpleDateFormat("YYYY-MM-dd");
                    movimientoT.setFecha_movimiento(formatoFecha2.format(fecha2));

                    movimientoT.setTipo(transferencia.getTipoCuentaDestino());

                    movimientosService.guardarMov(movimientoT);


                    cuentaDestino.setSaldo(Double.toString(saldoNuevoDestino)); 
                    cuentaService.guardarC(cuentaDestino);

                  }else{log.info("No se puede hacer la transferencia por saldo insuficiente");
                   }return "redirect:/";

            }else
                 { log.info("No se puede hacer la transferencia por saldo insuficiente");
                 }

         return "redirect:/";


       }catch (Exception e) {
          log.info("No es posible hacer la transferencia: alguna de la cuentas no existe");
          return "redirect:/";
        }
     }
}
