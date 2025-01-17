package paquete.conversor.api.peticion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ClientePeticionador {

    //    String urlDireccion = "https://v6.exchangerate-api.com/v6/6e5005eecc99966146980d87/latest/USD";

    //Concatenacion URLs y variables de monedas
    private String apiKey = "INGRESE_SU_APIKEY_AQUI";
    private String urlDireccionBase = "https://v6.exchangerate-api.com/v6/" + apiKey;
    private String monedaBase;
    private String monedaDeCambio;

    //Getters/Setters
//    public String getApiKey() {
//        return apiKey;
//    }
//    public void setApiKey(String apiKey) {
//        this.apiKey = apiKey;
//    }
//    public String getUrlDireccionBase() {
//        return urlDireccionBase;
//    }
//    public void setUrlDireccionBase(String urlDireccionBase) {
//        this.urlDireccionBase = urlDireccionBase;
//    }
//    public String getMonedaBase() {
//        return monedaBase;
//    }
//    public void setMonedaBase(String monedaBase) {
//        this.monedaBase = monedaBase;
//    }
//    public String getMonedaDeCambio() {
//        return monedaDeCambio;
//    }
//    public void setMonedaDeCambio(String monedaDeCambio) {
//        this.monedaDeCambio = monedaDeCambio;
//    }
    //Metodo Define como  va a ser la request dadas las monedas
    private HttpRequest construccionDePeticion(String monedaorigen, String monedaAConvertir){
        //Crea un nuevo constructor para configurar una solicitud HTTP.
        HttpRequest request = HttpRequest.newBuilder()

                .uri(URI.create(urlDireccionBase + "pair/" + monedaorigen + "/" + monedaAConvertir))
                .build(); //Construye el objeto HttpRequest con la configuración proporcionada.

        return request; //Devuelve el objeto HttpRequest creado, listo para ser usado en una solicitud HTTP.
    }
//Metodo que devolvera el resultado de la peticion
    public String obtenerResultadoTipoCambio(){
        //variable request que recibe el objeto obtenido al ejec el metod contruccionDePeticion
        var request = construccionDePeticion(monedaBase, monedaDeCambio);
            //Se crea el "posman"
            HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> respuestaDelServidor = client.send(request, HttpResponse.BodyHandlers.ofString());
            String respuestaJson = respuestaDelServidor.body();
            //Retorna el cuerpo de la respuesta en formato Json
            return respuestaJson;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
//Metodo configurar codigos
    public void configurarCodigosDeMonedas(String monedaBase, String monedaDeCambio){
        this.monedaBase = monedaBase;
        this.monedaDeCambio = monedaDeCambio;
    }

}
