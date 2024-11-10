public class Posicion {
    private int renglon, columna;

    public Posicion(int renglon, int columna){
        this.renglon = renglon;
        this.columna = columna;
    }

    public int getRenglon(){
        return this.renglon;
    }

    public int getColumna(){
        return this.columna;
    }

    @Override
    public String toString(){
        return getRenglon() + " " + getColumna();
    }
}
