package Main.Computador;

/*
Formato de instrucción (64 bits):
|  8 bits  |  8 bits  |  8 bits  |  8 bits  |      32 bits      |
|  opcode  |   r1     |   r2     |   r3     |   inmediato/ext   |
*/

public class Assembler {
    public static long instr(int opcode, int r1, int r2, int r3, long ext) {
        return ((ext & 0xFFFFFFFFL) << 32)
             | ((r3 & 0xFFL) << 24)
             | ((r2 & 0xFFL) << 16)
             | ((r1 & 0xFFL) << 8)
             | (opcode & 0xFFL);
    }

    // Instrucciones inmediatas comunes
    public static long movi(int r1, long val) {
        return instr(Opcodes.MOVI, r1, 0, 0, val);
    }

    public static long incrementar(int r1, long val) {
        return instr(Opcodes.INCREMENTAR, r1, 0, 0, val);
    }

    public static long decrementar(int r1, long val) {
        return instr(Opcodes.DECREMENTAR, r1, 0, 0, val);
    }

    public static long setBit(int r1, int bit) {
        return instr(Opcodes.SET_BIT, r1, 0, 0, bit);
    }

    public static long limpiarBit(int r1, int bit) {
        return instr(Opcodes.LIMPIAR_BIT, r1, 0, 0, bit);
    }

    public static long rotarIzq(int r1, int r2, int cant) {
        return instr(Opcodes.ROTAR_I, r1, r2, 0, cant);
    }

    public static long rotarDer(int r1, int r2, int cant) {
        return instr(Opcodes.ROTAR_D, r1, r2, 0, cant);
    }
}