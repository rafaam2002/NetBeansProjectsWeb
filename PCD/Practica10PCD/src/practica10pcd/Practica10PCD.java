/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica10pcd;

import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2AnyCallChannel;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.util.Buffer;

/**
 *
 * @author rafaa
 */
public class Practica10PCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Any2OneChannel entraperro = Channel.any2one(new Buffer(20));
        One2OneChannel permiso [] = Channel.one2oneArray(20, new Buffer(1));
    }
    
}
