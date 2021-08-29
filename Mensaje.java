public class Mensaje {

    Boolean respuesta;
    int valor;
    int porcentaje;
    int peso;
    double monto;

    public Mensaje (int valor, int peso, int porcentaje) {
        this.respuesta = false;
        this.valor = valor;
        this.porcentaje = porcentaje;
        this.peso = peso;
    }

    public Mensaje (double monto) {
        this.respuesta = true;
        this.monto = monto;
    }

    public String toString() {
        return respuesta.toString() + "," + String.valueOf(valor) + "," + String.valueOf(peso) + "," + String.valueOf(porcentaje) + "," + String.valueOf(monto);
    }

    public String toPrint() {
        if (respuesta) {
            return "La respuesta es: " + String.valueOf(monto) + "\n";
        }
        else {
            return "La solicitud es: " + String.valueOf(valor) + "," + String.valueOf(porcentaje) + "," + String.valueOf(peso) + "\n";
        }
    }

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

    public Mensaje calculate (){
        Mensaje msg2 = new Mensaje(((double)this.valor * (double)this.porcentaje / 100) + ((double)this.peso * 0.15));
        return msg2;
    }
}