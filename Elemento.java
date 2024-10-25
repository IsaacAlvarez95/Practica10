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
}