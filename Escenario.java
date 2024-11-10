
import java.util.ArrayList;


public class Escenario {
    
    private String nombre;
    private Elemento[][] campoDeBatalla;

    public Escenario(String nombre){
        campoDeBatalla = new Elemento[10][10];
        this.nombre = nombre;
    }

    public void addElemento(Elemento elemento){
        Posicion p = elemento.getPosicion();
        campoDeBatalla[p.getRenglon()][p.getColumna()] = elemento;
    }

    public void loadElemento(Elemento[][] elementos){
        Elemento elemento;
        for(int i = 0; i < elementos.length; i++){
            for(int j = 0; j < elementos[i].length; j++){
                if(elementos[i][j] != null){
                    Posicion p = elementos[i][j].getPosicion();
                    elemento = elementos[p.getRenglon()][p.getColumna()];
                    campoDeBatalla[p.getRenglon()][p.getColumna()] = elemento;
                }else{
                    campoDeBatalla[i][j] = null;
                }
            }
        }
        
    }

    public void destruirElementos(Posicion p, int r){
        ArrayList<Elemento> elementosDestruidos = new ArrayList<>();
        
        for (int i = p.getRenglon() - r; i <= p.getRenglon() + r ; i++) {
            for (int j = p.getColumna() - r; j <= p.getColumna() + r ; j++) {
                if(i >= 0 && i < campoDeBatalla.length
                && j >= 0 && j < campoDeBatalla[i].length){
                    if(campoDeBatalla[i][j] instanceof Destructible){
                        elementosDestruidos.add(campoDeBatalla[i][j]);
                        campoDeBatalla[i][j] = null;
                    }
                }
            }
        }

        for(Elemento e : elementosDestruidos){
            if(e instanceof Destructible){
                ((Destructible) e).destruir();
                System.out.println(((Destructible) e).destruir());
            }
        }
    
        
    }

    public void explotarBomba(Posicion p){
        Posicion pTemp;
        int radio;

        if(campoDeBatalla[p.getRenglon()][p.getColumna()] instanceof Bomba){
            radio = ((Bomba)campoDeBatalla[p.getRenglon()][p.getColumna()]).getRadio();
            ((Bomba)campoDeBatalla[p.getRenglon()][p.getColumna()]).explotar();

            for (int i = p.getRenglon() - radio; i <= p.getRenglon() + radio ; i++) {
                for (int j = p.getColumna() - radio; j <= p.getColumna() + radio ; j++) {
                    if(i >= 0 && i < campoDeBatalla.length
                    && j >= 0 && j < campoDeBatalla[i].length){
                        if(campoDeBatalla[i][j] instanceof Destructible){
                            campoDeBatalla[i][j] = null;
                        }
                    }
                }
            }
        }else{
            System.out.println("No hay ninguna bomba en esta posicion.");
        }
    }

    public void guardarEscenario(){
        Elemento.saveFile(this.nombre, campoDeBatalla);
    }

    @Override
    public String toString(){
        String matriz = "";
        for(int i = 0; i < campoDeBatalla.length ;i++){
            for(int j = 0; j < campoDeBatalla[i].length; j++){
                if(campoDeBatalla[i][j] instanceof Terricola){
                    matriz = matriz + " T";
                }else if(campoDeBatalla[i][j] instanceof Extraterrestre){
                    matriz = matriz + " E";
                }else if(campoDeBatalla[i][j] instanceof Roca){
                    matriz = matriz + " R";
                }else if(campoDeBatalla[i][j] instanceof Bomba){
                    matriz = matriz + " B";
                }else{
                    matriz = matriz + " 0";
                }
            }
            matriz = matriz + "\n";
        }
        return matriz;
    }

}
