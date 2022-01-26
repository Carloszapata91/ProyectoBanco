
package proyectos.bootcamp.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectos.bootcamp.entity.Cliente;
import proyectos.bootcamp.service.ClienteService;
import proyectos.bootcamp.repository.ClienteRepository;

@Service  //De esta manera se reconoce a esta clase como un servicio y la podre inyectar en el controlador
public class ClienteServiceImpl implements ClienteService {
  
    @Autowired
    private ClienteRepository usuarioDao;

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Cliente> listarUsuarios(){
         return (List<Cliente>) usuarioDao.findAll();
    } 

    @Override
    @Transactional   //Hace modificaciones)
    public void guardar (Cliente usuario){
        usuarioDao.save(usuario);
    }
    
    @Override
    @Transactional
    public void eliminar (Cliente usuario){
          usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly=true)
    public Cliente encontrarUsuario (Cliente usuario){
         return usuarioDao.findById(usuario.getId_usuario()).orElse(null);
    } 

   
 
}
