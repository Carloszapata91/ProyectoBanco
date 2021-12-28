
package proyectos.bootcamp.domain;

import java.io.Serializable;
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
@Table(name="usuario")  //Para evitar problemas entre el nombre de la tabla en base de datos (usuario) y el nombre de la clase (Usuario)
public class Usuario implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  idUsuario;
    private String nombre;
    private String apellidos;
    private String tipoIdentificacion; 
    private Double numeroIdentificacion;
    private String correoElectronico;
    private String fechaNacimiento;
    private String fechaCreacionCuenta;
    private Double telefono;
 

}
