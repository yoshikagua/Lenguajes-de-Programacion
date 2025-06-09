package Test.TestEnsamblador;

import Main.Computador.Computer;
import Main.Utilidades.LeerYCargarPrograma;
import Main.Utilidades.GeneradorBinario;

public class TestSumaArregloEnsamblador {
    public static void main(String[] args) {
        try {
            String rutaAsm = "Lenguajes-de-Programacion/Computador/src/Main/ProgramasEnsambladores/SumaArregloEnsamblador.asm";
            // Convierte el ensamblador a binario
            String rutaBin = GeneradorBinario.ensamblarArchivo(rutaAsm);
            int base = 0;
            Computer computer = new Computer();

            // Inicializa el arreglo [5, 7, 3] en 0x2000
            int[] arreglo = {5, 7, 3};
            int baseDatos = 0x2000 / Computer.WORD_SIZE;
            for (int i = 0; i < arreglo.length; i++) {
                computer.memory[baseDatos + i] = arreglo[i];
            }
            computer.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

            // Carga el binario generado
            LeerYCargarPrograma.cargarPrograma(rutaBin, base, computer);

            long resultado = computer.memory[0x1000 / Computer.WORD_SIZE];
            if (resultado == 15) {
                System.out.println("SumaArregloEnsamblador: OK (resultado = " + resultado + ")");
            } else {
                System.out.println("SumaArregloEnsamblador: FALLÓ (resultado = " + resultado + ")");
            }
        } catch (Exception e) {
            System.err.println("ERROR en el test: " + e.getMessage());
        }
    }
}