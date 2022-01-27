


package proyectos.bootcamp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyectos.bootcamp.entity.Cliente;
import proyectos.bootcamp.service.ClienteService;


@Controller   //Controlador MVC de Thymeleaf (tecnologia de despliegue) .. da el acceso al metodo
@Slf4j       //Facilita visualizar mensajes en la consola (log.console)
public class ClienteController {
    
    @Autowired  //Inyecto una dependencia administrada por otro contenedor -> Inyecto la interface UsuarioService en esta clase.. porque trabajo con la capa de negocio y no directamente con la capa de datos (usuarioDao)
    private ClienteService usuarioService;

      @GetMapping("/customers")
     public String crearCliente(Cliente cliente){
         return "modificar";
     }

     @PostMapping("/guardarCliente")
     public String guardarCliente (Cliente cliente,  RedirectAttributes redirectAttrs){
        cliente.setFecha_creacion_cuenta("2022-01-11");
        Date fecha=new Date();
        SimpleDateFormat  formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        cliente.setFecha_creacion_cuenta(formatoFecha.format(fecha));
        usuarioService.guardar(cliente);
          
        redirectAttrs
            .addFlashAttribute("mensaje", "Cliente guardado correctamente")
            .addFlashAttribute("clase", "success");
        //attribute.addFlashAttribute("sucess", "Cliente guardado exitosamente");
         log.info("200 OK - Cliente creado con exito ");
        return "201Created_1";
     }

     @GetMapping("/editarCliente/{id_usuario}")
     public String editar(Cliente cliente, Model model, RedirectAttributes attribute){
        cliente = usuarioService.encontrarUsuario(cliente);
        model.addAttribute("usuario", cliente);
       
        attribute.addFlashAttribute("success", "Cliente guardado exitosamente");
        
        return "modificar";
     }

    @GetMapping("/allCustomers")     //Solicitud GET (metodo de solicitud) para la consulta
    public String verUsuarios(Model model){ 
        
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "verUsuarios";
    }

    @GetMapping("/eliminar/{id_usuario}")     //Solicitud GET (metodo de solicitud) para la consulta
    public String eliminar(Cliente usuario){ 
        try{
        usuarioService.eliminar(usuario);
        log.info("Cliente eliminado exitosamente ");
        return "200OK";
     
        }catch (Exception e) {
          log.info("No es posible eliminar a este cliente, porque tiene productos activos");
          return "errorEliminarCliente";
        }
    }
}
