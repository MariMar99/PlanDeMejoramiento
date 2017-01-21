/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.backend.persistence.facades;

import co.plandemejoramiento.backend.persistence.entity.Vehiculo;
import co.plandemejoramiento.frontend.controller.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    
    public List<Vehiculo> listarVehiculosPorPrecio(int precio){
        Connection conn=Conexion.getInstance();
        List<Vehiculo> listaTodos= new ArrayList<>();
       String sql="SELECT * from vehiculo WHERE precio>?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, precio);
            ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getInt("modelo"));
            vehiculo.setPrecio(rs.getInt("precio"));
            listaTodos.add(vehiculo);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }   return listaTodos;
    }
    
}
