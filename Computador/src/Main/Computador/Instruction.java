package Main.Computador;

/*
Formato de instrucción (64 bits):
|  8 bits  |  8 bits  |  8 bits  |  8 bits  |      32 bits      |
|  opcode  |   r1     |   r2     |   r3     |   inmediato/ext   |
*/

public class Instruction {
    public static int getOpcode(long instr) {
        return (int) (instr & 0xFF);
    }

    public static int getReg1(long instr) {
        return (int) ((instr >> 8) & 0xFF);
    }

    public static int getReg2(long instr) {
        return (int) ((instr >> 16) & 0xFF);
    }

    public static int getReg3(long instr) {
        return (int) ((instr >> 24) & 0xFF);
    }

    public static long getExtended(long instr) {
        long ext = (instr >> 32) & 0xFFFFFFFFL;
        // Si el bit 31 está encendido, es negativo en 32 bits, haz sign-extend
        if ((ext & 0x80000000L) != 0) {
            ext |= 0xFFFFFFFF00000000L;
        }
        return ext;
    }
}
