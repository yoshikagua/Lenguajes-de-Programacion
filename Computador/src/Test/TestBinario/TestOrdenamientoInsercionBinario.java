package Test.TestBinario;

import Main.Computador.Computer;
import Main.Utilidades.LeerYCargarPrograma;

public class TestOrdenamientoInsercionBinario {
    public static void main(String[] args) {
        try {
            String ruta = "Lenguajes-de-Programacion/computador/src/Main/ProgramasBinarios/OrdenamientoInsercionBinario.txt";
            int base = 0;
            Computer computer = new Computer();

            // Inicializa el arreglo [7, 3, 5, 1] en 0x2000 (ajusta según tu algoritmo)
            int[] arreglo = {7, 3, 5, 1};
            int baseDatos = 0x2000 / Computer.WORD_SIZE;
            for (int i = 0; i < arreglo.length; i++) {
                computer.memory[baseDatos + i] = arreglo[i];
            }
            computer.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

            LeerYCargarPrograma.cargarPrograma(ruta, base, computer);

            // Verifica el resultado esperado: [1, 3, 5, 7]
            boolean ok = computer.memory[baseDatos] == 1 &&
                         computer.memory[baseDatos + 1] == 3 &&
                         computer.memory[baseDatos + 2] == 5 &&
                         computer.memory[baseDatos + 3] == 7;

            System.out.print("OrdenamientoInsercion: ");
            for (int i = 0; i < 4; i++) System.out.print(computer.memory[baseDatos + i] + " ");
            System.out.println(ok ? "\nOK" : "\nFALLÓ");
        } catch (Exception e) {
            System.err.println("ERROR en el test: " + e.getMessage());
        }
    }
}