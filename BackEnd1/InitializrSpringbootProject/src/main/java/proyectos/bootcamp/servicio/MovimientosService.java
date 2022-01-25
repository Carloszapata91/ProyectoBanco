package proyectos.bootcamp.servicio;

import java.util.List;
import proyectos.bootcamp.domain.Cuenta;
import proyectos.bootcamp.domain.Movimientos;



public interface MovimientosService {

    public List<Movimientos> listarMovimientos(Cuenta cuenta);

    public void guardarMov (Movimientos movimientos);
    
    public void eliminarMov (Movimientos movimientos);

    public Movimientos encontrarMov (Movimientos movimientos);
    
}
