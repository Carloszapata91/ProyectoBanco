/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.IdClass;

/**
 *
 * @author cocot
 */
@Data   //Genera todos los Getter y Setter de los atributos de la clase
@Entity
@Table(name="cuenta") 
//@IdClass(value = CuentaPK.class)
public class Cuenta implements Serializable{
    
    private static final long serialVersionUID= 1L;

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id_usuario;
    //@Id
    private String tipo;
    private String estado;
    private String fecha_apertura;
    private String saldo; 
    
}

