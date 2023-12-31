import java.util.Scanner;

/**
 * Esta clase funciona como ejecutable para probar la implementación del juego de la vida.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño del tablero en el universo: ");
        int n = scanner.nextInt();

        Universo genActual = new Universo(n);
        int i = 0;
        genActual.colocarVivas(n);
        System.out.println("Generación inicial:");
        System.out.println(genActual);
        while (i < 1000) {
            if (genActual.getCantVivas() == 0) break;
            Universo sigGen = genActual.aplicaReglas();
            //Thread.sleep((long) t * 1000);
            System.out.println("Evolución " + (i + 1) + ":");
            System.out.println(sigGen);
            genActual = new Universo(sigGen);
            i++;
        }
        String exitPorMuerte = "Han muerto todas las celulas. Fin del juego.";
        String exitPorRepeticion = "Se superaron las 1000 generaciones. Fin del juego";
        System.out.println(i == 1000 ? exitPorRepeticion : exitPorMuerte);
    }
}