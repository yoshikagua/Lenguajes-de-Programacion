package Main.Algoritmos;
import Main.Computador.Assembler;
import Main.Computador.Opcodes;

public class SumaArreglo {
    public static long[] generarPrograma() {
        return new long[]{
            Assembler.movi(1, 0x2000),  // R1 = dir base arreglo
            Assembler.movi(2, 3),       // R2 = longitud
            Assembler.movi(3, 0),       // R3 = suma
            Assembler.movi(4, 0),       // R4 = contador

            // Bucle:
            Assembler.instr(Opcodes.CMP, 4, 2, 0, 0),           // 4: compara contador con longitud
            Assembler.instr(Opcodes.SIZ, 0, 0, 0, 88),          // 5: si iguales, salta a FIN (9*8=72)
            Assembler.instr(Opcodes.CARGAR, 5, 1, 0, 0),        // 6: R5 = Mem[R1]
            Assembler.instr(Opcodes.SUMAR, 3, 3, 5, 0),         // 7: suma acumulador
            Assembler.instr(Opcodes.INCREMENTAR, 1, 0, 0, 8),   // 8: avanza puntero arreglo
            Assembler.instr(Opcodes.INCREMENTAR, 4, 0, 0, 1),   // 9: contador++
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, 32),       // 10: salta a inicio del bucle (4*8=32)

            // Fin:
            Assembler.instr(Opcodes.GUARDAR, 3, 0, 0, 0x1000),  // 11: guarda suma en 0x1000
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)           // 12: halt
        };
    }
}
