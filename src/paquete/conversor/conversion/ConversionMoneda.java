package paquete.conversor.conversion;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConversionMoneda {

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    //recibe un objeto Json para la conversion a un objeto Moneda
public MonedaRecord transformarRespuestaJsonAObjeto(String JsonObtenido){

         var jsonTransformado = gson.fromJson(JsonObtenido,MonedaRecord.class);
         return  jsonTransformado;

    }
}
