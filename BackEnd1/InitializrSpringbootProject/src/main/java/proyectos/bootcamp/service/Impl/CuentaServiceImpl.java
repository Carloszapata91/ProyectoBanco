/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.service.Impl;

import proyectos.bootcamp.entity.Cuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectos.bootcamp.entity.CuentaPK;
import proyectos.bootcamp.service.CuentaService;
import proyectos.bootcamp.repository.cuentaRepository;
/**
 *
 * @author cocot
 */
@Service
public class CuentaServiceImpl implements CuentaService{
     
    @Autowired
    private cuentaRepository cuentaDao;

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Cuenta> listarCuentas(){
         return (List<Cuenta>) cuentaDao.findAll();
    } 

    @Override
    @Transactional   //Hace modificaciones)
    public void guardarC (Cuenta cuenta){
        cuentaDao.save(cuenta);
    }
    
    @Override
    @Transactional
    public void eliminarC (Cuenta cuenta){
          cuentaDao.delete(cuenta);
    }

    @Override
    @Transactional(readOnly=true)
    public Cuenta encontrarCuenta (Cuenta cuenta){
         CuentaPK cuent = new CuentaPK();
          cuent.setId_usuario(cuenta.getId_usuario());
          cuent.setTipo(cuenta.getTipo());
         return cuentaDao.findById(cuenta.getId_usuario()).orElse(null);
    } 

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Cuenta> listarCuentas2(String a){
         return (List<Cuenta>) cuentaDao.findByCategory("algo");
    } 

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Cuenta> listarProductosByID(Cuenta cuenta){
         return (List<Cuenta>) cuentaDao.findByID2(cuenta.getId_usuario());
    }

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  UsuarioServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public Cuenta EncontrarByIDTipo(Cuenta cuenta){
         return (Cuenta) cuentaDao.findByIDTipo(cuenta.getId_usuario(), cuenta.getTipo());
    }

}
