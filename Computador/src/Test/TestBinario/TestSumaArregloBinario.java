package Test.TestBinario;

import Main.Computador.Computer;
import Main.Utilidades.LeerYCargarPrograma;

public class TestSumaArregloBinario {
    public static void main(String[] args) {
        try {
            String ruta = "Lenguajes-de-Programacion/computador/src/Main/ProgramasBinarios/SumaArregloBinario.txt";
            int base = 0;
            Computer computer = new Computer();

            // Inicializa el arreglo en memoria (ajusta los valores y la dirección según tu algoritmo)
            int[] arreglo = {5, 7, 3};
            int baseDatos = 0x2000 / Computer.WORD_SIZE;
            for (int i = 0; i < arreglo.length; i++) {
                computer.memory[baseDatos + i] = arreglo[i];
            }
            computer.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

            LeerYCargarPrograma.cargarPrograma(ruta, base, computer);

            long resultado = computer.memory[0x1000 / Computer.WORD_SIZE];
            if (resultado == 15) {
                System.out.println("Test PASÓ: resultado en memoria = " + resultado);
            } else {
                System.out.println("Test FALLÓ: resultado en memoria = " + resultado);
            }
        } catch (Exception e) {
            System.err.println("ERROR en el test: " + e.getMessage());
        }
    }
}