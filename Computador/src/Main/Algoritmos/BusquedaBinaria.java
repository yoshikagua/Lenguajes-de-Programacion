package Main.Algoritmos;
import Main.Computador.Assembler;
import Main.Computador.Opcodes;

public class BusquedaBinaria {
    public static long[] generarPrograma(int valorBuscar) {
        int base = 0x1000;
        return new long[]{
            Assembler.movi(1, 0x2000),        // 0
            Assembler.movi(2, 8),             // 1
            Assembler.movi(3, valorBuscar),   // 2
            Assembler.movi(4, -1),            // 3
            Assembler.movi(5, 0),             // 4
            Assembler.movi(6, 7),             // 5
            Assembler.movi(10, 1),            // 6

            // INICIO_BUCLE (índice 7)
            Assembler.instr(Opcodes.JG, 5, 6, 0, base + 21*8),        // 7: NO_ENCONTRADO
            Assembler.instr(Opcodes.SUMAR, 7, 5, 6, 0),               // 8
            Assembler.instr(Opcodes.SHR, 7, 7, 0, 1),                 // 9
            Assembler.instr(Opcodes.CARGAR, 8, 1, 7, 0),              // 10
            Assembler.instr(Opcodes.CMP, 8, 3, 0, 0),                 // 11
            Assembler.instr(Opcodes.SIZ, 0, 0, 0, base + 17*8),       // 12: ENCONTRADO
            Assembler.instr(Opcodes.JG, 8, 3, 0, base + 15*8),        // 13: BUSCAR_IZQUIERDA
            Assembler.instr(Opcodes.JL, 8, 3, 0, base + 19*8),        // 14: BUSCAR_DERECHA

            // BUSCAR_IZQUIERDA (índice 15)
            Assembler.instr(Opcodes.RESTAR, 6, 7, 10, 0),             // 15
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, base + 7*8),     // 16: INICIO_BUCLE

            // ENCONTRADO (índice 17)
            Assembler.instr(Opcodes.MOV, 4, 7, 0, 0),                 // 17
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0),                // 18

            // BUSCAR_DERECHA (índice 19)
            Assembler.instr(Opcodes.SUMAR, 5, 7, 10, 0),              // 19
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, base + 7*8),     // 20: INICIO_BUCLE

            // NO_ENCONTRADO (índice 21)
            Assembler.movi(4, -1),                                    // 21
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)                 // 22
        };
    }
}