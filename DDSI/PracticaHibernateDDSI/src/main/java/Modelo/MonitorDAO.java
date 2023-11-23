/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class MonitorDAO {

    private final Session session;

    public MonitorDAO(Session session) {
        this.session = session;
    }
}
