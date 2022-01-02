package proyectos.bootcamp.servicio;

import java.util.List;
import proyectos.bootcamp.domain.Movimientos;



public interface MovimientosService {

    public List<Movimientos> listarMovimientos();

    public void guardarMov (Movimientos movimientos);
    
    public void eliminarMov (Movimientos movimientos);

    public Movimientos encontrarMovimientos (Movimientos movimientos);
    
}
