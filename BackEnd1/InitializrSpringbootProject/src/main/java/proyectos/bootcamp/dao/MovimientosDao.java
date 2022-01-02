
package proyectos.bootcamp.dao;

import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.domain.Movimientos;

/**
 *
 * @author cocot
 */
public interface MovimientosDao extends CrudRepository<Movimientos, Long>{
    
}
