
package proyectos.bootcamp.service;

import java.util.List;
import proyectos.bootcamp.entity.Cliente;


public interface ClienteService {
    
    public List<Cliente> listarUsuarios();

    public void guardar (Cliente usuario);
    
    public void eliminar (Cliente usuario);

    public Cliente encontrarUsuario (Cliente usuario);

   
   
}
