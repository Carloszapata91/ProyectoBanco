
package proyectos.bootcamp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.domain.Cuenta;
import proyectos.bootcamp.domain.CuentaPK;


/**
 *
 * @author cocot
 */

public interface cuentaDao extends CrudRepository<Cuenta, Long>{    //CrudRepository me da acceso a las consultas mas habituales de SQL

    
   //  public Cuenta loadAllCuentas();
    //Aqui puedo definir todos los querys personalizados que necesite
}


