/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.dao;

import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.domain.Usuario;

/**
 *
 * @author cocot
 */
public interface UsuarioDao extends CrudRepository<Usuario, Long>{
    //Aqui puedo definir todos los querys personalizados que necesite
}
