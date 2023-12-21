/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import Objetos.DecoderMensaje;
import Objetos.EncoderMensaje;
import Objetos.Mensaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author rafaa
 */
@ServerEndpoint(value = "/chat", encoders = {EncoderMensaje.class},
        decoders = {DecoderMensaje.class})
public class miChat {

    private static final ArrayList<Session> conectados = new ArrayList<>();
    private static final Map<String, Session> conectadosMap = new HashMap();

    @OnOpen
    public void inicio(Session sesion) {
        System.out.println("Conectado");
//        conectados.add(sesion);
        System.out.println(sesion);
    }

    @OnClose
    public void salir(Session session) {
        System.out.println("Desconectado");
//        conectados.remove(session);
    eliminarPorValor(session);

    }

    @OnMessage
    public void mensaje(Mensaje mensaje, Session session) throws IOException, EncodeException {
//        System.out.println("Se mete en mensaje");
        System.out.println(mensaje.getMensaje());

        //si el receptor es "" o null significa la session es nueva
        if (mensaje.getnReceptor() == null || mensaje.getnReceptor().equals("")) {
            //si por algun casual ya estuviese ese nombre de usuario en el diccionario se borra para darle la nueva
            //session
            if (conectadosMap.containsKey(mensaje.getnEmisor())) {
                conectadosMap.remove(mensaje.getnEmisor());
            }
            conectadosMap.put(mensaje.getnEmisor(), session);
            System.out.println("Nombre nuevo Concectado: " + mensaje.getnEmisor());
            System.out.println("Sesion nuevo Conectado: " + session.getId());
        } else {
            //cojo las sessiones de emisor y receptor y les paso el mensaje
            conectadosMap.get(mensaje.getnEmisor()).getBasicRemote().sendObject(mensaje);
            conectadosMap.get(mensaje.getnReceptor()).getBasicRemote().sendObject(mensaje);

        }
    }
    private void eliminarPorValor( Session session) {
        Iterator<Map.Entry<String, Session>> iterator = conectadosMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Session> entrada = iterator.next();
            if (session.equals(entrada.getValue())) {
                iterator.remove(); // Eliminar la entrada por valor
            }
        }
    }
   
}
