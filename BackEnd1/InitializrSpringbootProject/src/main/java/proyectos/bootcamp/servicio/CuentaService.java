/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.servicio;

import java.util.List;
import proyectos.bootcamp.domain.Cuenta;



public interface CuentaService {

    public List<Cuenta> listarCuentas();

    public void guardarC (Cuenta cuenta);
    
    public void eliminarC (Cuenta cuenta);

    public Cuenta encontrarCuenta (Cuenta cuenta);
}


