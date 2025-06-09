package Test.TestEnsamblador;

import Main.Computador.Computer;
import Main.Utilidades.LeerYCargarPrograma;
import Main.Utilidades.GeneradorBinario;

public class TestBusquedaBinariaEnsamblador {
    public static void main(String[] args) {
        try {
            String rutaAsm = "Lenguajes-de-Programacion/Computador/src/Main/ProgramasEnsambladores/BusquedaBinariaEnsamblador.asm";
            // Convierte el ensamblador a binario
            String rutaBin = GeneradorBinario.ensamblarArchivo(rutaAsm);
            int base = 0x1000; // Cargar en 0x1000 igual que el test Java
            Computer computer = new Computer();

            // Inicializa el arreglo ordenado [1, 3, 5, 7, 9, 11, 13, 15] en 0x2000
            int[] arreglo = {1, 3, 5, 7, 9, 11, 13, 15};
            int baseDatos = 0x2000 / Computer.WORD_SIZE;
            for (int i = 0; i < arreglo.length; i++) {
                computer.memory[baseDatos + i] = arreglo[i];
            }
            computer.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

            LeerYCargarPrograma.cargarPrograma(rutaBin, base, computer);

            // El algoritmo debe dejar el índice del valor encontrado (por ejemplo, buscar 11, índice 5) en R4
            int esperado = 5; // Cambia este valor si tu ensamblador busca otro número
            System.out.println("BusquedaBinariaEnsamblador: R4 = " + computer.registers[4]);
            if (computer.registers[4] == esperado) {
                System.out.println("Test PASÓ: índice encontrado = " + computer.registers[4]);
            } else {
                System.out.println("Test FALLÓ: índice encontrado = " + computer.registers[4]);
            }
        } catch (Exception e) {
            System.err.println("ERROR en el test: " + e.getMessage());
        }
    }
}