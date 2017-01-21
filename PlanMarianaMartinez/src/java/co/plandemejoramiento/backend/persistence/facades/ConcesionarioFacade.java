/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.backend.persistence.facades;

import co.plandemejoramiento.backend.persistence.entity.Concesionario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    //Inicio Sesion (Usuario es Igual a Concesionario)
    public Concesionario iniciarSesion(Concesionario con){
        Concesionario concesionario = null;
        String consulta;
        try {
            consulta = "FROM Concesionario c WHERE c.nit=?1  and c.clave=?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, con.getNit());
            query.setParameter(2, con.getClave());
            
            List<Concesionario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                concesionario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        //finally{
          //  em.close();}   ---- Se quita porque ya estan las notaciones @EJB
        return concesionario;
    }
    
}
