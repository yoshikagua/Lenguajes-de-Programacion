package Test.TestAlgoritmos;

import Main.Computador.Computer;
import Main.Algoritmos.BusquedaBinaria;

public class TestBusquedaBinaria {
    public static void main(String[] args) {
        // Inicializa el arreglo ordenado en memoria [1, 3, 5, 7, 9, 11, 13, 15] en 0x2000
        long[] arreglo = {1, 3, 5, 7, 9, 11, 13, 15};
        int[] pruebas = {4, 11};
        int[] esperados = {-1, 5};

        for (int i = 0; i < pruebas.length; i++) {
            Computer comp = new Computer();
            int base = 0x2000 / Computer.WORD_SIZE;
            for (int j = 0; j < arreglo.length; j++) {
                comp.memory[base + j] = arreglo[j];
            }
            comp.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

            comp.loadProgram(BusquedaBinaria.generarPrograma(pruebas[i]), 0x1000);
            comp.PC = 0x1000;
            comp.registers[4] = -2;

            comp.execute();

            long resultado = comp.registers[4];
            boolean ok = (resultado == esperados[i]);
            System.out.println("Buscando " + pruebas[i] + ": Resultado = " + resultado + " (esperado: " + esperados[i] + ") - " + (ok ? "OK" : "FALLÓ"));
            System.out.println();
        }
    }
}
