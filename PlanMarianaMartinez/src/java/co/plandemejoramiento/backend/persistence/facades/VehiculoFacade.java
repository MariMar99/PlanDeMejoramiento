/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.backend.persistence.facades;

import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mariana
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "PlanMarianaMartinezPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }
    
    public List<Vehiculo> consultarPrecio(Integer precio){
        return em.createNamedQuery("Vehiculo.validarPrecio").setParameter("precio", precio).getResultList();
    }
    
}
