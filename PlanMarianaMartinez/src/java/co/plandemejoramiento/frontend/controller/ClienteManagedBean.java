package co.plandemejoramiento.frontend.controller;

import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import co.plandemejoramiento.backend.persistence.entity.Cliente;
import co.plandemejoramiento.backend.persistence.facades.ClienteFacade;
import co.plandemejoramiento.frontend.converter.IManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author Mariana
 */
@Named(value = "clienteManagedBean")
//@SessionScoped
@RequestScoped
public class ClienteManagedBean implements Serializable, IManagedBean<Cliente> {

    @EJB private ClienteFacade clientefc;
    private Cliente cliente;
    
    public ClienteManagedBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @PostConstruct
    public void init(){
        cliente = new Cliente();
    }
    
    public void registrarCliente(){
        try {
            clientefc.create(cliente);
        } catch (Exception e) {
        }
    }
    
    public void eliminarCliente(Cliente cliente){
        try {
            clientefc.remove(cliente);
        } catch (Exception e) {
        }
    }
    
    public void modificarCliente(Cliente cliente){
        try {
            clientefc.edit(cliente);
        } catch (Exception e) {
        }
    }
    
    public List<Cliente> listarCliente(){
        try {
            return clientefc.findAll();
        } catch (Exception e) {
        }return null;
    }

    @Override
    public Cliente getObject(Integer i) {
        return clientefc.find(i);
    }
            
}
