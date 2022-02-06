
package proyectos.bootcamp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectos.bootcamp.entity.Usuario;
import proyectos.bootcamp.repository.UsuarioRepository;
import proyectos.bootcamp.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarrioDao;

        @Override
        @Transactional 
        public void guardarUsuario (Usuario usuarrio){
        usuarrioDao.save(usuarrio);
        }
        
        @Override
        @Transactional
        public void eliminarUsuario (Usuario usuarrio){
        usuarrioDao.delete(usuarrio);
        }
        
        @Override
        @Transactional(readOnly=true)
        public Usuario encontrarUsuarrio (Usuario usuarrio){
        return usuarrioDao.findUsuario(usuarrio.getUserName(), usuarrio.getContrasena());
        } 

}
