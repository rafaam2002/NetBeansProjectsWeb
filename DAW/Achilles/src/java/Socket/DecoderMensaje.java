package Socket;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author rafaa
 */
public class DecoderMensaje implements Decoder.TextStream<Mensaje> {

    @Override
    public Mensaje decode(Reader reader) throws DecodeException, IOException {
        Mensaje mensaje = new Mensaje();
        try ( JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            mensaje.setnEmisor(json.getString("nEmisor"));
            mensaje.setnReceptor(json.getString("nReceptor"));
            mensaje.setText(json.getString("text"));
            mensaje.setFecha(json.getString("fecha"));
            mensaje.setIdentificador(json.getString("identificador"));

        }
        return mensaje;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }

}
