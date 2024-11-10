public abstract class Personaje extends Elemento implements Destructible{

    protected String nombre;
    protected int puntosDeVida;

    public Personaje(String nombre, Escenario e, Posicion p){
        super(e, p);
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    @Override
    public String destruir(){
        return getNombre() + " ha sido destruido";
    }

}
