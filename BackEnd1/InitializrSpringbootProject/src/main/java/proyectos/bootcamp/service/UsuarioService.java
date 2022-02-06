
package proyectos.bootcamp.service;


import proyectos.bootcamp.entity.Usuario;


public interface UsuarioService {
    
        public void guardarUsuario (Usuario usuarrio);
    
        public void eliminarUsuario (Usuario usuarrio);


        public Usuario encontrarUsuarrio (Usuario usuarrio);

}
