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

    @OnOpen
    public void inicio(Session sesion) {
        System.out.println("Conectado");
        conectados.add(sesion);
    }

    @OnClose
    public void salir(Session session) {
        System.out.println("Desconectado");
        conectados.remove(session);
    }

    @OnMessage
    public void mensaje(Mensaje mensaje) throws IOException, EncodeException {
        for (Session conectado : conectados) {
            conectado.getBasicRemote().sendObject(mensaje);
        }
    }
}
