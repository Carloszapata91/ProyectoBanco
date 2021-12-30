/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.servicio;


import proyectos.bootcamp.dao.cuentaDao;
import proyectos.bootcamp.domain.Cuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author cocot
 */
public class CuentaServiceImplementacion implements CuentaService{
     
    @Autowired
    private cuentaDao cuentaDao;

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Cuenta> listarCuentas(){
         return (List<Cuenta>) cuentaDao.findAll();
    } 

    @Override
    @Transactional   //Hace modificaciones)
    public void guardar (Cuenta cuenta){
        cuentaDao.save(cuenta);
    }
    
    @Override
    @Transactional
    public void eliminar (Cuenta cuenta){
          cuentaDao.delete(cuenta);
    }

    @Override
    @Transactional(readOnly=true)
    public Cuenta encontrarCuenta (Cuenta cuenta){
         return cuentaDao.findById(cuenta.getId_usuario()).orElse(null);
    } 

}
