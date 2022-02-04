
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id;
    private String userName;
    private String contrasena;



}