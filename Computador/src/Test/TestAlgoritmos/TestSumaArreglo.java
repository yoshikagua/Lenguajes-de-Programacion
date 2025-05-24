package Test.TestAlgoritmos;
import Main.Computador.Computer;
import Main.Algoritmos.SumaArreglo;

public class TestSumaArreglo {
    public static void main(String[] args) {
        Computer comp = new Computer();

        // Inicializa el arreglo [5, 7, 3] en 0x2000
        int base = 0x2000 / Computer.WORD_SIZE;
        comp.memory[base] = 5;
        comp.memory[base + 1] = 7;
        comp.memory[base + 2] = 3;

        // IMPORTANTE: Inicializa el stack pointer (SP) si el algoritmo usa PUSH/POP/LLAMAR/RETORNAR
        comp.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

        // Carga y ejecuta el programa
        comp.loadProgram(SumaArreglo.generarPrograma(), 0);
        comp.execute();

        // Verifica el resultado
        long resultado = comp.memory[0x1000 / Computer.WORD_SIZE];
        if (resultado == 15) {
            System.out.println("SumaArreglo: OK (resultado = " + resultado + ")");
        } else {
            System.out.println("SumaArreglo: FALLÓ (resultado = " + resultado + ")");
        }
    }
}
