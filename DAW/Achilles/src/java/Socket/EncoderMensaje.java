package Socket;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author rafaa
 */
public class EncoderMensaje implements Encoder.TextStream<Mensaje> {

    @Override
    public void encode(Mensaje object, Writer writer) throws EncodeException, IOException {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("nEmisor", object.getnEmisor());
        jsonBuilder.add("nReceptor", object.getnReceptor());
        jsonBuilder.add("text", object.getText());
        jsonBuilder.add("fecha", object.getFecha());
        jsonBuilder.add("identificador", object.getIdentificador());
        
        JsonObject json = jsonBuilder.build();
        try ( JsonWriter jsonWriter = Json.createWriter(writer)) {
            jsonWriter.writeObject(json);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
