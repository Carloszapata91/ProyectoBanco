
package proyectos.bootcamp.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data   //Genera todos los Getter y Setter de los atributos de la clase
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
       
     private static final long serialVersionUID= 1L;

    @Id
    private String userName;
    private Long  id;
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private String numeroDoc;
    
    private String contrasena;



}
