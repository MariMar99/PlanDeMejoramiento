package co.plandemejoramiento.frontend.controller;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import co.plandemejoramiento.backend.persistence.facades.ConcesionarioFacade;
import co.plandemejoramiento.frontend.converter.IManagedBean;
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
@Named(value = "concesionarioManagedBean")
@SessionScoped
public class ConcesionarioManagedBean implements Serializable, IManagedBean<Concesionario> {

    @EJB private ConcesionarioFacade concesionariofc;
    private Concesionario concesionario;
    
    public ConcesionarioManagedBean() {
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    
    @PostConstruct
    public void init(){
        concesionario = new Concesionario();
    }
    
    public void registrarConcesionario(){
        try {
            concesionariofc.create(concesionario);
        } catch (Exception e) {
        }
    }
    
    public void eliminarConcesionario(Concesionario concesionario){
        try {
            concesionariofc.remove(concesionario);
        } catch (Exception e) {
        }
    }
    
    public void modificarConcesionario(Concesionario concesionario){
        try {
            concesionariofc.edit(concesionario);
        } catch (Exception e) {
        }
    }
    
    public List<Concesionario> listarConcesionario(){
        try {
            return concesionariofc.findAll();
        } catch (Exception e) {
        }return null;
    }

    @Override
    public Concesionario getObject(Integer i) {
        return concesionariofc.find(i);
    }
            
}
