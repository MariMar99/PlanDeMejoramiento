package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.facades.VehiculoFacade;
import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import co.plandemejoramiento.frontend.converter.IManagedBean;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
//import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mariana
 */
@Named (value = "vehiculoManagedBean")
//@SessionScoped
//@RequestScoped
@ViewScoped
public class VehiculoManagedBean implements Serializable, IManagedBean<Vehiculo> {

    @EJB private VehiculoFacade vehiculofc;
    private Vehiculo vehiculo;
    private int precio;   //variable para la consulta de precio
    private List<Vehiculo>  lista;
    public VehiculoManagedBean() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<Vehiculo> getLista() {
        return lista;
    }

    public void setLista(List<Vehiculo> lista) {
        this.lista = lista;
    }
    

    @PostConstruct
    public void init(){
        vehiculo = new Vehiculo();
    }
    
    public List<Vehiculo> listarVehiculo(){
        try {
            return vehiculofc.findAll();
        } catch (Exception e) {
        }return null;
    }
    
    public void registrarVehiculo(){
        try {
            vehiculofc.create(vehiculo);
            mensajeExito(" Vehiculo Registrado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void modificarVehiculo(Vehiculo vehiculo){
        try {
            vehiculofc.edit(vehiculo);
            mensajeExito(" Editado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    public void eliminarVehiculo(Vehiculo vehiculo){
        try {
            vehiculofc.remove(vehiculo);
            mensajeExito(" Vehiculo Eliminado ");
        } catch (Exception e) {
            mensajeError(e);
        }
    }
    
    //Implementar Consulta
    //consulta por entities
    public void preciosAll(){
//         Vehiculo v = vehiculofc.consultarPrecio(vehiculo.getPrecio()).get(0);         
          lista = vehiculofc.consultarPrecio(precio);
          //vehiculo = vehiculofc.consultarPrecio(vehiculo.getPrecio()).get(precio);
          System.out.println("Realizo Consulta de Precio por Concesionario"+getPrecio());
    }
    //consulta directa con conexión
    //public String listarVehiculosPrecio(){
    //   lista = vehiculofc.listarVehiculosPorPrecio(precio);
    //   System.out.println("Consulta completa de Precio por Concesionario "+lista);
    //   return "";        
    //}
    //consulta modeloreciente
    public String modeloReciente(){
        lista = vehiculofc.modeloReciente();
        System.out.println("Consulta Modelo Reciente" +lista);
        return "";
    }
    
    //Converter
    @Override
    public Vehiculo getObject(Integer i) {
        return vehiculofc.find(i);
    }
    
    //Mensajes
    private void mensajeError(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se ha Producido el siguiente Error: ", e.getMessage()));
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Insertar:", e.getMessage());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
    }

    private void mensajeExito(String operacion) {
        String msg = "Se ha realizado exitosamente la operacion de " + operacion;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO,"Opereción con Exito : ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);
    }

    
}
