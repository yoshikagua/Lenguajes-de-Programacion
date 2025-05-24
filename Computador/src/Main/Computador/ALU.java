package Main.Computador;

public class ALU {
    // --- Instrucciones aritméticas ---
    public long add(long a, long b) {
        return a + b;
    }

    public long sub(long a, long b) {
        return a - b;
    }

    public long mul(long a, long b) {
        return a * b;
    }

    public long div(long a, long b) {
        return b != 0 ? a / b : 0;
    }

    // --- Instrucciones lógicas ---
    public long and(long a, long b) {
        return a & b;
    }

    public long or(long a, long b) {
        return a | b;
    }

    public long xor(long a, long b) {
        return a ^ b;
    }

    public long not(long a) {
        return ~a;
    }

    // --- Instrucciones de desplazamiento ---
    public long shl(long a, int shift) {
        return a << shift;
    }

    public long shr(long a, int shift) {
        return a >> shift;
    }

    // --- Instrucciones de rotación ---
    public long rotarIzq(long a, int shift) {
        return Long.rotateLeft(a, shift);
    }

    public long rotarDer(long a, int shift) {
        return Long.rotateRight(a, shift);
    }

    // --- Instrucciones de manipulación de bits ---
    public long setBit(long a, int bit) {
        return a | (1L << bit);
    }

    public long limpiarBit(long a, int bit) {
        return a & ~(1L << bit);
    }

    public long buscarBit(long a) {
        return Long.numberOfTrailingZeros(a);
    }

    // --- Extras centralizables ---
    public long negar(long a) {
        return -a;
    }

    public long clear() {
        return 0;
    }

    public void swap(long[] regs, int r1, int r2) {
        long tmp = regs[r1];
        regs[r1] = regs[r2];
        regs[r2] = tmp;
    }

    public long cmp(long a, long b) {
        return a - b;
    }
}