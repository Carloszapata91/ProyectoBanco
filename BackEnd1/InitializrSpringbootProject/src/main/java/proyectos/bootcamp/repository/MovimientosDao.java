
package proyectos.bootcamp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;

/**
 *
 * @author cocot
 */
public interface MovimientosDao extends CrudRepository<Movimientos, Long>{
    @Query("from Movimientos m where m.id_usuario = :id_usuario and m.tipo = :tipo")
   public List<Movimientos> findTipoCuenta(@Param("id_usuario") Long id_usuario, @Param("tipo") String tipo);
}
