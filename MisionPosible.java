import java.util.*;

public class MisionPosible {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int options = 0;
		
		System.out.println("- JUEGO INICIADO -\n");
    	Escenario e = new Escenario("Nostromo");
		e.loadElemento(Elemento.readFile("Nostromo"));
		do { 
			System.out.println("- Estado del campo de batalla : -");
			System.out.println(e);

			System.out.println("- Seleccione una opcion : - \n" +
								"<1> - Guardar \n" +
								"<2> - Cargar \n" +
								"<3> - Seleccionar Bomba \n" +
								"<4> - Salir");
			options = sc.nextInt();
			switch (options) {
				case 1: //Guardar
					e.guardarEscenario();
					System.out.println("- GUARDADO -\n");
					break;
				case 2: //Cargar
					e.loadElemento(Elemento.readFile("Nostromo"));
					System.out.println("- CARGADO -\n");
					break;
				case 3: //Explotar Bombra
					Posicion p;
					int x = 0;
					int y = 0;
					do{
						System.out.println("- Seleccione el renglon : -");
						x = sc.nextInt() - 1;
						System.out.println("- Seleccione la columna : -");
						y = sc.nextInt() - 1;
						p = new Posicion(x, y);
						e.explotarBomba(p);
					}while(x < 0 || x >= 10 || y < 0 || y >= 10);
					break;
				case 4:
				System.out.println("- JUEGO FINALIZADO -\n");
					break;
				default:
					System.out.println("- OPCION INVALIDA -\n");
					
			}
		} while (options != 4);
    
		
	}

}
