
package proyectos.bootcamp.repository;

import org.springframework.data.repository.CrudRepository;
import proyectos.bootcamp.entity.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    
}
