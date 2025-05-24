package Main.Algoritmos;
import Main.Computador.Assembler;
import Main.Computador.Opcodes;

public class OrdenamientoInsercion {
    public static long[] generarPrograma() {
        return new long[]{
            Assembler.movi(1, 0x2000), // R1 = base arreglo
            Assembler.movi(2, 4),      // R2 = longitud n
            Assembler.movi(3, 1),      // R3 = i = 1

            // INICIO_BUCLE_EXTERNO (PC=24)
            Assembler.instr(Opcodes.CMP, 3, 2, 0, 0),         // 3: if (i >= n) fin
            Assembler.instr(Opcodes.JGE, 3, 2, 0, 184),       // 4: salto a FIN (23*8=184)

            Assembler.instr(Opcodes.CARGAR, 4, 1, 3, 0),      // 5: key = arr[i]
            Assembler.instr(Opcodes.MOV, 5, 3, 0, 0),         // 6: j = i
            Assembler.instr(Opcodes.DECREMENTAR, 5, 0, 0, 1), // 7: j--

            // INICIO_BUCLE_INTERNO (PC=64)
            Assembler.instr(Opcodes.CMP, 5, 0, 0, 0),         // 8: if (j < 0) fin_j
            Assembler.instr(Opcodes.JL, 5, 0, 0, 144),        // 9: salto a FIN_J (18*8=144)
            Assembler.instr(Opcodes.CARGAR, 6, 1, 5, 0),      // 10: temp = arr[j]
            Assembler.instr(Opcodes.CMP, 6, 4, 0, 0),         // 11: if (arr[j] <= key) fin_j
            Assembler.instr(Opcodes.JLE, 6, 4, 0, 144),       // 12: salto a FIN_J (18*8=144)

            // arr[j+1] = arr[j]
            Assembler.instr(Opcodes.SUMAR, 7, 5, 0, 0),       // 13: R7 = j
            Assembler.instr(Opcodes.INCREMENTAR, 7, 0, 0, 1), // 14: R7 = j + 1
            Assembler.instr(Opcodes.GUARDAR, 6, 1, 7, 0),     // 15: arr[j+1] = arr[j]
            Assembler.instr(Opcodes.DECREMENTAR, 5, 0, 0, 1), // 16: j--
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, 64),     // 17: salto a INICIO_BUCLE_INTERNO (8*8=64)

            // FIN_J (PC=144)
            Assembler.instr(Opcodes.SUMAR, 7, 5, 0, 0),       // 18: R7 = j
            Assembler.instr(Opcodes.INCREMENTAR, 7, 0, 0, 1), // 19: R7 = j + 1
            Assembler.instr(Opcodes.GUARDAR, 4, 1, 7, 0),     // 20: arr[j+1] = key
            Assembler.instr(Opcodes.INCREMENTAR, 3, 0, 0, 1), // 21: i++
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, 24),     // 22: salto a INICIO_BUCLE_EXTERNO (3*8=24)

            // FIN (PC=184)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)         // 23: halt
        };
    }
}
