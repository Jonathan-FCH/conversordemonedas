package paquete.conversor.principal;

import paquete.conversor.api.peticion.ClientePeticionador;
import paquete.conversor.conversion.ConversionMoneda;
import paquete.conversor.conversion.MonedaClase;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final Scanner scannerLectura = new Scanner(System.in);
    private static List<String> historialConverciones = new ArrayList<>();
    private static int variableEjecucionPrograma = 1;

    //Implementacion para obtener info de fecha y hora
    static LocalDateTime horaLocal = LocalDateTime.now();
    static DateTimeFormatter formatoHhora = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
    static String horaLocalFormateada = horaLocal.format(formatoHhora);

    public static void main(String[] args) {

        //Ciclo ejecucion del programa
        do {
        //creando objeto peticionador y objeto conversor
        ConversionMoneda objetoConversor = new ConversionMoneda();
        ClientePeticionador objetoClientePeticionador = new ClientePeticionador();

        String menu = """
                ////////////////////////////////////////////////////////////////
                
                1) Colón ==> Dolar
                2) Dolar ==> Colón
                3) Colón ==> Euro
                4) Euro  ==> Colón
                5) Peso Argentino  ==> Boliviano
                6) Real Brasileño  ==> Peso Chileno
                7) Peso Colombiano  ==> Dolar
                
                *) Historial de Conversiones
                0) Salir
                
                Elija una opcion valida
                
                ////////////////////////////////////////////////////////////////
        
                """;

        System.out.println("Bienvendido a su conversor de monedas");
        System.out.println(menu);
        var datosIngresadosPorUsuario = scannerLectura.nextLine();

        switch (datosIngresadosPorUsuario) {

            case "1":
                //Configura los codigos de monedas en el obj cliente peticionador
                objetoClientePeticionador.configurarCodigosDeMonedas("CRC","USD");
                //Ejec metodo general
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Colones","Dolares");
                break;
            case "2":
                objetoClientePeticionador.configurarCodigosDeMonedas("USD","CRC");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Dolares","Colones");
                break;
            case "3":
                objetoClientePeticionador.configurarCodigosDeMonedas("CRC","EUR");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Colones","Euros");
                break;
            case "4":
                objetoClientePeticionador.configurarCodigosDeMonedas("EUR","CRC");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Euros","Colones");
                break;
            case "5":
                objetoClientePeticionador.configurarCodigosDeMonedas("ARS","BOB");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Pesos Argentinos","Bolivianos");
                break;
            case "6":
                objetoClientePeticionador.configurarCodigosDeMonedas("BRL","CLP");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Reales Brasileños","Pesos Chilenos");
                break;
            case "7":
                objetoClientePeticionador.configurarCodigosDeMonedas("COP","USD");
                metodoGeneralConversion(objetoClientePeticionador,objetoConversor,"Pesos Colombianos","Dolares");
                break;
            case "*":
                //Muestra en consola linea por linea el historial de la lista
                historialConverciones.forEach(System.out::println);
                break;
            case "0":
                variableEjecucionPrograma = 0;
                System.out.println("Gracias por su visita");
                break;
        }

        //fin del cliclo
        }while(variableEjecucionPrograma != 0);
    }



    //Metodo General Conversion/////////////////////////////////////////////////////////////////////////////////////
    public static void metodoGeneralConversion(ClientePeticionador objetoClientePeticionador,
                                               ConversionMoneda objetoConversor,
                                               String monedaBase,
                                               String monedaDestinoStrin
    ){

        //guarda en una variable resultado del metodo ClientePeticionador que devuelve el json del API
        var respuestaJson = objetoClientePeticionador.obtenerResultadoTipoCambio();
        //guarda en variable la conversion del json a clase moneda obtenido a clase moneda
        var convercionRespuestaJsonAObjeto = objetoConversor.transformarRespuestaJsonAObjeto(respuestaJson);

        System.out.println("Ingrese el monto en " + monedaBase);

        //captura de monto digitado por el usuario
        var montoIngresadoPorUsuario = Integer.parseInt(scannerLectura.nextLine());

        //crea objeto Moneda
        MonedaClase objetoMoneda = new MonedaClase(monedaBase ,montoIngresadoPorUsuario);
        //ejecuta metodo de calculo que necesita recibir un objeto json de respuesta
        objetoMoneda.calcularTipoCambio(convercionRespuestaJsonAObjeto);

        //Almacena convercion en la lista-historial
        historialConverciones.add(montoIngresadoPorUsuario + " " +  monedaBase +  " equivale a "+ objetoMoneda.calcularTipoCambio(convercionRespuestaJsonAObjeto) + " " +  monedaDestinoStrin+ " --- " + horaLocalFormateada);
        //muestra el resultado de la convercion
        System.out.println(montoIngresadoPorUsuario + " " +  monedaBase +  " equivale a "+ objetoMoneda.calcularTipoCambio(convercionRespuestaJsonAObjeto) + " " +  monedaDestinoStrin+ " " + horaLocalFormateada);
    }
}

