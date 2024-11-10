import java.io.*;

public abstract class Elemento {
    protected Posicion posicion;
    protected Escenario escenario;

    public Elemento (Escenario escenario, Posicion posicion){
        this.escenario = escenario;
        this.posicion = posicion;
    }

    public Posicion getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Posicion posicion){
        this.posicion = posicion;
    }

    public static void saveFile(String nombreArchivo, Elemento[][] configuracion){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nombreArchivo));
            for(int i = 0; i < configuracion.length; i++){
                for(int j = 0; j < configuracion[i].length; j++){
                    if(configuracion[i][j] instanceof Terricola){
                        writer.write("Terricola " + configuracion[i][j].getPosicion() +
                        " " + ((Personaje) configuracion[i][j]).getNombre());
                        writer.newLine();
                    }
                    if(configuracion[i][j] instanceof Extraterrestre){
                        writer.write("Extraterrestre " + configuracion[i][j].getPosicion() +
                        " " + ((Personaje) configuracion[i][j]).getNombre());
                        writer.newLine();
                    }
                    if(configuracion[i][j] instanceof Roca){
                        writer.write("Roca " + configuracion[i][j].getPosicion());
                        writer.newLine();
                    }
                    if(configuracion[i][j] instanceof Bomba){
                        writer.write("Bomba " + configuracion[i][j].getPosicion() + " " +
                         ((Bomba) configuracion[i][j]).getRadio());
                        writer.newLine();
                    }
                }
            }
            //System.out.println("Configuración escrita en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            //System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                //System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }
        

    public static Elemento[][] readFile(String nombreArchivo) {
        Elemento[][] configuracion = new Elemento[10][10];
        Escenario es = new Escenario(nombreArchivo);
        BufferedReader reader = null;

        Terricola t;
        Extraterrestre ex;
        Roca r;
        Bomba b;

        try {
            reader = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] lineaDiv = linea.split("\\s+");

                switch (lineaDiv[0]) {
                    case "Terricola":
                        es.addElemento(new Terricola(lineaDiv[3], es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2])))) ;

                        t = new Terricola(lineaDiv[3], es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2])));

                        configuracion[Integer.parseInt(lineaDiv[1])][Integer.parseInt(lineaDiv[2])] = t;
                        break;

                    case "Extraterrestre":
                        es.addElemento(new Extraterrestre(lineaDiv[3], es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2]))));

                        ex = new Extraterrestre(lineaDiv[3], es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2])));

                        configuracion[Integer.parseInt(lineaDiv[1])][Integer.parseInt(lineaDiv[2])] = ex;
                        break;

                    case "Roca":
                        es.addElemento(new Roca(es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2]))));
                        
                        r = new Roca(es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2])));

                        configuracion[Integer.parseInt(lineaDiv[1])][Integer.parseInt(lineaDiv[2])] = r;
                        break;

                    case "Bomba":
                    es.addElemento(new Bomba(Integer.parseInt(lineaDiv[3]), es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2]))));
                        
                        b = new Bomba(Integer.parseInt(lineaDiv[3]), es,
                        new Posicion(Integer.parseInt(lineaDiv[1]), Integer.parseInt(lineaDiv[2])));

                        configuracion[Integer.parseInt(lineaDiv[1])][Integer.parseInt(lineaDiv[2])] = b;
                        break;

                    default:
                        System.err.println("Tipo de elemento desconocido: " + lineaDiv[0]);
                        break;
                }
            }
            // System.out.println("Configuración leída del archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
        return configuracion;
    }
}
