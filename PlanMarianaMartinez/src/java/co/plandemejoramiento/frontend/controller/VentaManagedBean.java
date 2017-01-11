package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.entity.Venta;
import co.plandemejoramiento.backend.persistence.facades.VentaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Mariana
 */
@Named(value = "ventaManagedBean")
@SessionScoped
public class VentaManagedBean implements Serializable {

    @EJB private VentaFacade ventafc;
    private Venta venta;
    
    public VentaManagedBean() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    @PostConstruct
    public void init(){
        venta = new Venta();
    }
    
    public void registrarVenta(){
        try {
            ventafc.create(venta);
        } catch (Exception e) {
        }
    }
    
    public void eliminarVenta(Venta venta){
        try {
            ventafc.remove(venta);
        } catch (Exception e) {
        }
    }
    
    public void modificarVenta(Venta venta){
        try {
            ventafc.edit(venta);
        } catch (Exception e) {
        }
    }
    
    public List<Venta> listarVenta(){
        try {
            return ventafc.findAll();
        } catch (Exception e) {
        }return null;
    }
            
}
