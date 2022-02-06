
package proyectos.bootcamp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import proyectos.bootcamp.entity.Movimientos;
import proyectos.bootcamp.entity.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
      @Query("from Usuario u where u.userName = :user_Name ")
   public Usuario findUsuario(@Param("user_Name") String userName);
}
