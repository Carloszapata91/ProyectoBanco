import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperacionesComponent } from './operaciones.component';

describe('OperacionesComponent', () => {
  let component: OperacionesComponent;
  let fixture: ComponentFixture<OperacionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperacionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

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
import proyectos.bootcamp.entity.Transferencia;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.service.MovimientosService;
import proyectos.bootcamp.service.ClienteService;


@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class TransferenciaController {

    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping("/transfer")
    public String crearTransferencia (Transferencia transferencia){
          return "crearTransferencia";
    }

    @PostMapping("/guardarTransferencia")
    public String guardarTransferencia (Movimientos movimientos, Cuenta cuenta, Transferencia transferencia){
      
      if(Double.parseDouble(transferencia.getCantidad())>=0){
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
                    
                if ( (cuentaOrigen.getTipo().equals("Corriente")&& (Double.parseDouble(cuentaOrigen.getSaldo())- (1.004*Double.parseDouble(transferencia.getCantidad())))>= (-2000000)) || ( cuentaOrigen.getTipo().equals("Ahorros")&& (Double.parseDouble(cuentaOrigen.getSaldo())- (1.004*Double.parseDouble(transferencia.getCantidad())))>=0)  ) { 
                    
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
                    return "200OK";
                  
                }else{log.info("2 - No se puede hacer la transferencia por saldo insuficiente");
                   }return "fondosInsuficientes";

            }else
                 { log.info("No se puede hacer la transferencia por cuenta inactiva o cancelada");
                 } return "cuentaInactivaCancelada";


       }catch (Exception e) {
          log.info("No es posible hacer la transferencia: alguna de las cuentas no existe");
          return "501ISE_2";
        }

      }else{return"501ISE_3";}
    }


}
