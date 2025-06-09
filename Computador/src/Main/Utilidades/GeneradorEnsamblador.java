package Main.Utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Clase utilitaria para exportar programas generados en Java a archivos ensamblador
public class GeneradorEnsamblador {

    /**
     * Convierte un arreglo de instrucciones (long[]) a un archivo ensamblador (.asm)
     * en el directorio relativo ProgramasEnsambladores, usando el nombre dado.
     * El archivo se llamará igual que el nombre base seguido de "Ensamblador.asm"
     * Cada línea del archivo tendrá el formato: opcode r1 r2 r3 ext
     */
    public static void exportarPrograma(long[] instrucciones, String nombreBase) throws IOException {
        // Ruta relativa para ProgramasEnsambladores (desde el directorio de trabajo)
        String dir = "Lenguajes-de-Programacion/Computador/src/Main/ProgramasEnsambladores";
        File carpeta = new File(dir);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        String ruta = dir + File.separator + nombreBase + "Ensamblador.asm";
        try (PrintWriter out = new PrintWriter(new FileWriter(ruta))) {
            for (long instr : instrucciones) {
                // Extraer campos (formato: [ext(32)][r3][r2][r1][opcode])
                int opcode = (int)(instr & 0xFF);
                int r1 = (int)((instr >> 8) & 0xFF);
                int r2 = (int)((instr >> 16) & 0xFF);
                int r3 = (int)((instr >> 24) & 0xFF);
                long ext = (instr >> 32) & 0xFFFFFFFFL;
                out.printf("%d %d %d %d %d%n", opcode, r1, r2, r3, ext);
            }
        }
        System.out.println("Archivo ensamblador generado en: " + ruta);
    }
}