package paquete.conversor.conversion;

public class MonedaClase {

    private String nombre;
    private double valor;

    public MonedaClase(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    //getters/setters
//    public String getNombre() {
//        return nombre;
//    }
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//    public double getValor() {
//        return valor;
//    }
//    public void setValor(double valor) {
//        this.valor = valor;
//    }

    public double calcularTipoCambio(MonedaRecord objetoMonedarRecord) {

        double resultadoTipoCambio;
        return resultadoTipoCambio = valor * objetoMonedarRecord.conversion_rate();

    }
//FIN
}
