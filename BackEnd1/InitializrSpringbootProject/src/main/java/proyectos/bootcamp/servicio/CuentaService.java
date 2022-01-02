package proyectos.bootcamp.servicio;

import java.util.List;
import proyectos.bootcamp.domain.Cuenta;



public interface CuentaService {

    public List<Cuenta> listarCuentas();

    public void guardarC (Cuenta cuenta);
    
    public void eliminarC (Cuenta cuenta);

    public Cuenta encontrarCuenta (Cuenta cuenta);
}


