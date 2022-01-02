
package proyectos.bootcamp.dao;

import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.domain.Cuenta;


/**
 *
 * @author cocot
 */
public interface cuentaDao extends CrudRepository<Cuenta, Long>{    //CrudRepository me da acceso a las consultas mas habituales de SQL
    //Aqui puedo definir todos los querys personalizados que necesite
}


