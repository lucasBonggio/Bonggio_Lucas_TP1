import java.text.DecimalFormat;
import java.util.Scanner;
public class App {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);        
        boolean salirPrograma = false;
        do { 
            eleccionUnidades();
            System.out.println("Desea hacer otra conversión? (SI/NO)");
            String respuesta = sc.nextLine().toUpperCase();

            if (respuesta.equals("NO")) {
                salirPrograma = true;
            } else if (!respuesta.equals("SI")) {
                System.out.println("Opción inválida. Por favor ingrese 'SI' o 'NO'.");
            }
        } while (!salirPrograma);
    }
    
    /**
     * Este procedimiento nos permite elegir la unidad inicial, destino y la cantidad que desea calcular
     */
    public static void eleccionUnidades(){
        String[] unidades = {"Bits", "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes"};
    
        Scanner sc = new Scanner(System.in);
        
        mostrarOpciones(unidades);

        System.out.println("Ingrese la unidad de origen: ");

        String unidadInicial = sc.nextLine().toLowerCase();
        
        DecimalFormat df = new DecimalFormat("#.######");
        
        if(verificacionIngreso(unidades, unidadInicial)){
            System.out.println("Ingrese la unidad destino: ");
            String unidadFinal = sc.nextLine().toLowerCase();

            if(verificacionIngreso(unidades, unidadFinal)){
                System.out.println("Ahora la cantidad a convertir: ");
                String cantidadInicialString = sc.nextLine();
                try{
                    double cantidadInicialNumerica = Double.parseDouble(cantidadInicialString);
                    double cantidadFinal = retornoConversionAKilobytes(unidadInicial, cantidadInicialNumerica);
                    double resultadoConversion = retornoMultiplicacion(unidadFinal, cantidadFinal);

                    System.out.println("La conversion de " + df.format(cantidadInicialNumerica)+ " " +  unidadInicial + " es igual a " + df.format(resultadoConversion) + " " + unidadFinal);   
                } catch (NumberFormatException e){
                    System.out.println("Ocurrió un error. Debes ingresar un dato númerico. ");
                }
                }else{
                    System.out.println("Ocurrió un error. Debes ingresar una unidad válida. ");
                }
                
            }else{
                System.out.println("Ocurrió un error. Debes ingresar una unidad válida.");
            }
        }

    /**
     * Transforma la unidad ingresada a kilobytes
     * @param unidadInicial La unidad que ingresa el usuario, nos sirve para identificar los operaciones
     * @param cantidadInicial La cantidad ingresada por el usuario
     * @return Estas operaciones nos devuelven la cantidad transformada a kilobytes
     */
    public static double retornoConversionAKilobytes(String unidadInicial, double cantidadInicial){
        switch (unidadInicial) {
            case "bits":
                    return cantidadInicial / (8 * 1024) ;
            case "bytes":
                return cantidadInicial / 1024;
            case "kilobytes":
                return cantidadInicial;
            case "megabytes":
                return cantidadInicial * 1024;
            case "gigabytes":
                return cantidadInicial * 1024 * 1024;
            case "terabytes":
                return cantidadInicial * (1024 * 1024 * 1024);
            default:
                return -1;
        }
    }

    /**
     * Convierte la cantidad de kilobytes a la unidad seleccionada.
     * @param unidadFinal La unidad a la que queremos transformar la unidad inicial.
     * @param cantidadFinal Es la unidad heredada desde la función de conversión a kilobytes.
     * @return El retorno nos dará el resultado de la conversión desde kB al tipo elegido.
     */
    public static double retornoMultiplicacion(String unidadFinal, double cantidadFinal) {
        Scanner sc = new Scanner(System.in);
        switch (unidadFinal) {
            case "bits":
                return cantidadFinal * 8;
            case "bytes":
                return cantidadFinal * 1024;
            case "kilobytes":
                return cantidadFinal;
            case "megabytes":
                return cantidadFinal / 1024;
            case "gigabytes":
                return cantidadFinal / (1024 * 1024);
            case "terabytes":
                return cantidadFinal / (1024 * 1024 * 1024);
            default:
                return -1;
        }
    }
    
    /**
     * Verifica si el usuario ingresó una unidad válida
     * @param unidades Vector que contiene todas las unidades de conversión
     * @param unidadInicial Unidad que elige el usuario que pasaría a ser verificada para ver si es correcta.
     * @return Retorna true si la unidad es correcta y false si no lo es
     */
    public static boolean verificacionIngreso(String[] unidades, String unidadInicial){
        for(String u:unidades){
            if(unidadInicial.equalsIgnoreCase(u)) return true;
        }
        return false;
    }
    
    /**
     * Esta procedimiento nos muestra las unidades disponibles
     * @param unidades Vector que contiene todas las unidades.
     * Nos imprime las unidades 
     */
    public static void mostrarOpciones(String[] unidades){
        System.out.println("Las unidades disponibles son: ");
        for (String u : unidades) {
            System.out.println("· " + u);
        }
    }
}