package proyectos.bootcamp.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;
import proyectos.bootcamp.service.MovimientosService;
import proyectos.bootcamp.repository.MovimientosRepository;


@Service  //De esta manera se reconoce a esta clase como un servicio y la podre inyectar en el controlador
public class MovimientosServiceImpl implements MovimientosService{
    
    @Autowired
    private MovimientosRepository movimientosDao;

    @Override  //Ahora el controlador NO usa la capa de datos, si no la capa de servicio  MovimientosServiceImplemen (capa negocio)
    @Transactional(readOnly=true) 
    public List<Movimientos> listarMovimientos(Cuenta cuenta){
         return (List<Movimientos>) movimientosDao.findTipoCuenta(cuenta.getId_usuario(),cuenta.getTipo());
    } 

    @Override
    @Transactional   //Hace modificaciones)
    public void guardarMov (Movimientos movimientos){
        movimientosDao.save(movimientos);
    }
    
    @Override
    @Transactional
    public void eliminarMov (Movimientos movimientos){
          movimientosDao.delete(movimientos);
    }

    @Override
    @Transactional(readOnly=true)
    public Movimientos encontrarMov (Movimientos movimientos){
         return movimientosDao.findById(movimientos.getId_usuario()).orElse(null);
    } 

 
}
