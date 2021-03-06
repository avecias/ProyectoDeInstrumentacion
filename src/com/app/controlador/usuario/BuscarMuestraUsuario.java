/*
 * Traera todas las muestras que ha generado el usuario
 * y se mostrará en una pequeña tabla
 */
package com.app.controlador.usuario;

import com.app.controlador.sesion.Sesion;
import com.app.modelo.conexion.db.ConexionSQL;
import com.app.modelo.dao.MuestraDAOImple;
import com.app.modelo.entidades.Muestra;
import com.app.modelo.entidades.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public class BuscarMuestraUsuario {
    
    public void get(Sesion sesion, JTable tabla){
        try{
            Usuario usuario = sesion.getUsuario();
            ConexionSQL mySQL = sesion.getConexionSQL();
            List<Muestra> lista = new MuestraDAOImple().todoPorUsuario(mySQL.getConexion(), usuario.getIdUsuario());
            tabla = new JTable(lista.size(), 4);
            for (int i = 0; i < lista.size(); i++) {
                Muestra muestra = lista.get(i);
                tabla.setValueAt(muestra.getIdMuestra(), i, 0);
                tabla.setValueAt(muestra.getNombre(), i, 1);
                tabla.setValueAt(muestra.getDescripcion(), i, 2);
                tabla.setValueAt(muestra.getFecha(), i, 3);
            }
        }
        catch(NullPointerException e){
            //Error al rescatar al usario subido a sesion
            System.err.println("Error al bajar de la session al usuario o la  conexion" + e);
        } catch (SQLException ex) {
            System.err.println("Error en la conexion para sacar las muestras por usuario " + ex);
        }
    }
}
