package proyectos.bootcamp.service;

import java.util.List;
import proyectos.bootcamp.entity.Cuenta;
import proyectos.bootcamp.entity.Movimientos;



public interface MovimientosService {

    public List<Movimientos> listarMovimientos(Cuenta cuenta);

    public void guardarMov (Movimientos movimientos);
    
    public void eliminarMov (Movimientos movimientos);

    public Movimientos encontrarMov (Movimientos movimientos);
    
}
