/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.backend.persistence.facades;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mariana
 */
@Stateless
public class ConcesionarioFacade extends AbstractFacade<Concesionario> {

    @PersistenceContext(unitName = "PlanMarianaMartinezPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConcesionarioFacade() {
        super(Concesionario.class);
    }
    
    
}
