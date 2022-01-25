
package proyectos.bootcamp.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectos.bootcamp.dao.UsuarioDao;
import proyectos.bootcamp.domain.Usuario;

@Service  //De esta manera se reconoce a esta clase como un servicio y la podre inyectar en el controlador
public class UsuarioServiceImplementacion implements UsuarioService {
  
    @Autowired
    private UsuarioDao usuarioDao;

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Usuario> listarUsuarios(){
         return (List<Usuario>) usuarioDao.findAll();
    } 

    @Override
    @Transactional   //Hace modificaciones)
    public void guardar (Usuario usuario){
        usuarioDao.save(usuario);
    }
    
    @Override
    @Transactional
    public void eliminar (Usuario usuario){
          usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly=true)
    public Usuario encontrarUsuario (Usuario usuario){
         return usuarioDao.findById(usuario.getId_usuario()).orElse(null);
    } 

   
 
}
