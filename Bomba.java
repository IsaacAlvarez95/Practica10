public class Bomba extends Elemento implements Destructible {
    
    private int radio;

    public Bomba(int r, Escenario e, Posicion p){
        super(e , p);
        this.radio = r;
    }

    public void explotar(){
        escenario.destruirElementos(posicion, radio);
    }
    
    @Override
    public String destruir(){
        return "Bomba ha sido destruida";
    }

    public int getRadio(){
        return radio;
    }


}
