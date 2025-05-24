package Main.Computador;

import java.nio.ByteBuffer;

public class Loader {
    public static String loadProgram(byte[] binary, int baseAddress, Computer computer) {
        if (readInt(binary, 0) != 0x7F454C46) {
            error("Formato de archivo inválido");
            return null;
        }

        long entryPoint = readLong(binary, 6);
        int sizeInWords = readShort(binary, 14);

        if (baseAddress + sizeInWords > computer.memory.length) {
            error("Memoria insuficiente");
            return null;
        }

        for (int i = 0; i < sizeInWords; i++) {
            long instruction = readLong(binary, 16 + i * 8);
            computer.memory[(baseAddress / 8) + i] = instruction; // <-- CORREGIDO
        }

        computer.PC = entryPoint;
        computer.FLAGS = 0;

        computer.execute();
        return "Carga exitosa";
    }

    private static int readInt(byte[] data, int offset) {
        return ByteBuffer.wrap(data, offset, 4).getInt();
    }

    private static long readLong(byte[] data, int offset) {
        return ByteBuffer.wrap(data, offset, 8).getLong();
    }

    private static int readShort(byte[] data, int offset) {
        return ByteBuffer.wrap(data, offset, 2).getShort();
    }

    private static void error(String msg) {
        System.err.println("ERROR: " + msg);
        System.exit(1);
    }
}