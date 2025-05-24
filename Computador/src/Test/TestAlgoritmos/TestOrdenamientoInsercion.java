package Test.TestAlgoritmos;
import Main.Computador.Computer;
import Main.Algoritmos.OrdenamientoInsercion;

public class TestOrdenamientoInsercion {
    public static void main(String[] args) {
        Computer comp = new Computer();

        // Inicializa el arreglo [7, 3, 5, 1] en 0x2000
        int base = 0x2000 / Computer.WORD_SIZE;
        comp.memory[base] = 7;
        comp.memory[base + 1] = 3;
        comp.memory[base + 2] = 5;
        comp.memory[base + 3] = 1;

        // IMPORTANTE: Inicializa el stack pointer (SP) si tu algoritmo usa PUSH/POP/LLAMAR/RETORNAR
        comp.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE;

        // Carga y ejecuta el programa
        comp.loadProgram(OrdenamientoInsercion.generarPrograma(), 0);
        comp.execute();

        // Verifica el resultado esperado: [1, 3, 5, 7]
        boolean ok = comp.memory[base] == 1 && comp.memory[base + 1] == 3 &&
                     comp.memory[base + 2] == 5 && comp.memory[base + 3] == 7;

        System.out.print("OrdenamientoInsercion: ");
        for (int i = 0; i < 4; i++) System.out.print(comp.memory[base + i] + " ");
        System.out.println(ok ? "\nOK" : "\nFALLÓ");
    }
}