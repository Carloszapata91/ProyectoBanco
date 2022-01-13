/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos.bootcamp.domain;

import lombok.Data;

@Data
public class Transferencia {
    private Long  cuentaOrigen;
    private Long  cuentaDestino; 
    private String  tipoCuentaOrigen;
    private String tipoCuentaDestino;
    private String cantidad;
    private String descripcion;
  
}