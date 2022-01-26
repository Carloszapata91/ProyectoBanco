/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.service.MovimientosService;
import proyectos.bootcamp.service.ClienteService;

@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class MovimientosController {
    
    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientosService movimientosService;
 

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
}
