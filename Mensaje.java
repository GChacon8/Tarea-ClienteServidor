/**
 * La clase Mensaje se encarga de ser un intermediario entre los mensajes del cliente y el servidor.
 * Transforma e interpreta los diferentes datos mandados.
 * @author Gabriel Chacon Alfaro
 */
public class Mensaje {

    Boolean respuesta;
    int valor;
    int porcentaje;
    int peso;
    double monto;
    /**
     * En esta clase se instancian objetos de tipo Mensaje.
     * 
     * @param valor - valor ingresado del producto
     * @param peso - peso ingresado del producto
     * @param porcentaje - porcentaje ingresado del impuestos
     */
    public Mensaje (int valor, int peso, int porcentaje) {
        this.respuesta = false;
        this.valor = valor;
        this.porcentaje = porcentaje;
        this.peso = peso;
    }
    /**
     * Cuando Mensaje solamente recibe un parametro se sabe que va a ser una respuesta.
     * De esta manera respuesta es true, o sea, que los datos son de tipo respuesta.
     * @param monto
     */
    public Mensaje (double monto) {
        this.respuesta = true;
        this.monto = monto;
    }
    /**
     * En este metodo se pasan los objetos instanciados en Mensaje a tipo String para que pasen por el "puente del socket"
     * 
     * @return - Retorna un string indicando el valor, peso y porcentaje del producto
     * 
     */
    public String toString() {
        return respuesta.toString() + "," + String.valueOf(valor) + "," + String.valueOf(peso) + "," + String.valueOf(porcentaje) + "," + String.valueOf(monto);
    }
    /**
     * En este metodo se recibe un string y se interpreta para mostrar ya sea la respuesta o los valores de solicitud.
     * 
     * @return - Retorna un String que sera mostrado en pantalla con los valores de cada caso.
     */
    public String toPrint() {
        if (respuesta) {
            return "La respuesta es: " + String.valueOf(monto) + "\n";
        }
        else {
            return "La solicitud es: " + String.valueOf(valor) + "," + String.valueOf(porcentaje) + "," + String.valueOf(peso) + "\n";
        }
    }
    /**
     * Este metodo recibe un string, lo interpreta y transforma en objeto.
     * 
     * @param str - El string que recibe con los valores
     * @return - Retorna un objeto Mensaje, ya sea de tipi respuesta o con los valores.
     */
    public static Mensaje fromString (String str) {
        String[] stringArray = str.split(",");
        Boolean respuesta = Boolean.parseBoolean(stringArray[0]);
        int valor = Integer.parseInt(stringArray[1]);
        int porcentaje = Integer.parseInt(stringArray[3]);
        int peso = Integer.parseInt(stringArray[2]);
        double monto = Double.parseDouble(stringArray[4]);
        if (respuesta){
            return new Mensaje(monto);
        }
        else{
            return new Mensaje(valor, peso, porcentaje);
        }
    }
    /**
     * Este metodo calcula el monto solicitado con base en los valores de un objeto Mensaje.
     * @return - Retorna un objeto Mensaje que es de tipo respueta (con el monto calculado).
     */
    public Mensaje calculate (){
        Mensaje msg2 = new Mensaje(((double)this.valor * (double)this.porcentaje / 100) + ((double)this.peso * 0.15));
        return msg2;
    }
}