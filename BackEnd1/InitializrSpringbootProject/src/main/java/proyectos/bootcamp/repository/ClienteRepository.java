/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.repository;

import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.entity.Cliente;

/**
 *
 * @author cocot
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long>{    //CrudRepository me da acceso a las consultas mas habituales de SQL
    //Aqui puedo definir todos los querys personalizados que necesite
}


