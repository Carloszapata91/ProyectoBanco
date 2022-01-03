package proyectos.bootcamp.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


/**
 *
 * @author cocot
 */

@Data   //Genera todos los Getter y Setter de los atributos de la clase
@Entity
@Table(name="movimientos") 
public class Movimientos implements Serializable{
      private static final long serialVersionUID= 1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id_movimiento;
    private Long  id_cuenta;
    private String saldo_inicial;
    private String tipo_movimiento;
    private double cantidad;
    private String fecha_movimiento;
    private String saldo_actual;
    
}


