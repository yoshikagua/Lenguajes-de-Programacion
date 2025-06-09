package Test.TestBinario;

import Main.Computador.Computer;
import Main.Utilidades.LeerYCargarPrograma;

public class TestCargarBinario {
    public static void main(String[] args) {
        try {
            String ruta = "Lenguajes-de-Programacion/computador/src/Main/ProgramasBinarios/CargarValorBinario.txt";
            int base = 0;
            Computer computer = new Computer();
            LeerYCargarPrograma.cargarPrograma(ruta, base, computer);

            // Verifica el valor de los registros después de la ejecución
            System.out.println("R1 = " + computer.registers[1]);
            System.out.println("R2 = " + computer.registers[2]);

            // Ejemplo de aserción manual
            if (computer.registers[1] == 15) {
                System.out.println("Test PASÓ: R1 tiene el valor esperado (15)");
            } else {
                System.out.println("Test FALLÓ: R1 tiene el valor " + computer.registers[1]);
            }

        } catch (Exception e) {
            System.err.println("ERROR en el test: " + e.getMessage());
        }
    }
}