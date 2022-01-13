
package proyectos.bootcamp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import proyectos.bootcamp.domain.Cuenta;
import proyectos.bootcamp.domain.CuentaPK;


/**
 *
 * @author cocot
 */

public interface cuentaDao extends CrudRepository<Cuenta, Long>{    //CrudRepository me da acceso a las consultas mas habituales de SQL

    @Query("from Cuenta c where c.id_usuario = 2") //Test1
   public List<Cuenta> findByCategory(@Param("categoryName") String categoryName);
   
    //  public Cuenta loadAllCuentas();
    //Aqui puedo definir todos los querys personalizados que necesite

   @Query("from Cuenta c where c.id_usuario = :id_usuario")
   public List<Cuenta> findByID2(@Param("id_usuario") Long id_usuario); 

   @Query("from Cuenta c where c.id_usuario = :id_usuario and c.tipo = :tipo")
   public Cuenta findByIDTipo(@Param("id_usuario") Long id_usuario, @Param("tipo") String tipo);
   
}


