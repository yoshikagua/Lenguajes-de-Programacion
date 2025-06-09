import java.util.Scanner;

import Main.Computador.Computer;
import Main.Utilidades.GeneradorBinario;
import Main.Utilidades.LeerYCargarPrograma;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Simulador de Computador ===");
            System.out.println("1. Convertir archivo ensamblador (.asm) a binario (.txt)");
            System.out.println("2. Cargar y ejecutar archivo binario (.txt)");
            System.out.println("3. Cargar y ejecutar archivo ensamblador (.asm)");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.print("Ruta del archivo ensamblador (.asm): ");
                String rutaAsm = sc.nextLine();
                try {
                    String rutaBinTxt = GeneradorBinario.ensamblarArchivo(rutaAsm);
                    System.out.println("Archivo binario generado: " + rutaBinTxt);
                } catch (Exception e) {
                    System.err.println("ERROR al ensamblar: " + e.getMessage());
                }
            } else if (opcion == 2) {
                System.out.print("Ruta del archivo binario (.txt): ");
                String ruta = sc.nextLine();
                System.out.print("Dirección base de carga (en bytes): ");
                int base = Integer.parseInt(sc.nextLine());

                Computer computer = new Computer();
                try {
                    LeerYCargarPrograma.cargarPrograma(ruta, base, computer);
                    System.out.println("Programa ejecutado correctamente.");
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
            } else if (opcion == 3) {
                System.out.print("Ruta del archivo ensamblador (.asm): ");
                String rutaAsm = sc.nextLine();
                System.out.print("Dirección base de carga (en bytes): ");
                int base = Integer.parseInt(sc.nextLine());
                try {
                    String rutaBinTxt = GeneradorBinario.ensamblarArchivo(rutaAsm);
                    Computer computer = new Computer();
                    LeerYCargarPrograma.cargarPrograma(rutaBinTxt, base, computer);
                    System.out.println("Programa ensamblador ejecutado correctamente.");
                } catch (Exception e) {
                    System.err.println("ERROR al ejecutar ensamblador: " + e.getMessage());
                }
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}