package proyectos.bootcamp.service;

import java.util.List;
import proyectos.bootcamp.entity.Cuenta;



public interface CuentaService {

    public List<Cuenta> listarCuentas();

    public void guardarC (Cuenta cuenta);
    
    public void eliminarC (Cuenta cuenta);

    public Cuenta encontrarCuenta (Cuenta cuenta);

    public List<Cuenta> listarCuentas2(String a);

   public List<Cuenta> listarProductosByID(Cuenta cuenta);

   public Cuenta EncontrarByIDTipo(Cuenta cuenta);
}


