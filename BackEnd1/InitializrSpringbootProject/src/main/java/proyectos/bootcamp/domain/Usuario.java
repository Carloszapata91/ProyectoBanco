
package proyectos.bootcamp.domain;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author cocot
 */
@Data   //Genera todos los Getter y Setter de los atributos de la clase
@Entity
@Table(name="usuario")
public class Usuario {
    private String nombre;
    private String apellidos;
    private String tipoIdentificacion; 
    private Double numeroIdentificacion;
    private String correoElectronico;
    private String fechaNacimiento;
    private String fechaCreacionCuenta;
    private Double telefono;
 

}
