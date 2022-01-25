
package proyectos.bootcamp.service;

import java.util.List;
import proyectos.bootcamp.entity.Usuario;


public interface UsuarioService {
    
    public List<Usuario> listarUsuarios();

    public void guardar (Usuario usuario);
    
    public void eliminar (Usuario usuario);

    public Usuario encontrarUsuario (Usuario usuario);

   
   
}
