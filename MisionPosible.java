public class MisionPosible {
    public static void main(String[] args) {
    	Escenario e = new Escenario("Nostromo");
    	e.addElemento(new Terricola("Ripley" , e, new Posicion(3, 2)));
    	e.addElemento(new Extraterrestre("Alien", e,new Posicion(3,5)));
    	e.addElemento(new Roca(e, new Posicion(4,3)));
    	Bomba b = new Bomba(1, e, new Posicion(4,4));
    	e.addElemento(b);
    	System.out.println(e);
    	b.explotar();
	}

}
