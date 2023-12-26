package Socket;

import java.io.IOException;
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
public class Chat {

//    @PersistenceContext(unitName = "AchillesPU")
//    private EntityManager em;
//    @Resource
//    private javax.transaction.UserTransaction utx;

//    private static final ArrayList<Session> conectados = new ArrayList<>();
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
        //si el receptor es "" o null significa la session es nueva
        if (mensaje.getnReceptor() == null || mensaje.getnReceptor().equals("")) {
            //si por algun casual ya estuviese ese nombre de usuario en el diccionario se borra para darle la nueva
            //session
            if (conectadosMap.containsKey(mensaje.getnEmisor())) {
                conectadosMap.remove(mensaje.getnEmisor());
                System.out.println("Usuario ha cambiado de sesion");
            }
            conectadosMap.put(mensaje.getnEmisor(), session);
            System.out.println("Nombre nuevo Concectado: " + mensaje.getnEmisor());
            System.out.println("Sesion nuevo Conectado: " + session.getId());
        } else {
            //intento pasarle el mensaje al receptor, si existe
            try {
                conectadosMap.get(mensaje.getnReceptor()).getBasicRemote().sendObject(mensaje);
                //cojo las session del emisor y le paso el mensaje en caso de que el receptor exista
                conectadosMap.get(mensaje.getnEmisor()).getBasicRemote().sendObject(mensaje);

            } catch (IOException | EncodeException | NullPointerException e) {
                System.out.println("El usuario Receptor no se encuentra conectado");
            }
        }
    }

    private void eliminarPorValor(Session session) {
        Iterator<Map.Entry<String, Session>> iterator = conectadosMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Session> entrada = iterator.next();
            if (session.equals(entrada.getValue())) {
                iterator.remove(); // Eliminar la entrada por valor
            }
        }
    }

}
