package Main.Utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GeneradorBinario {
    // Ensamblador binario según el formato: [ext (32 bits)][r3][r2][r1][opcode]
    public static long instr(int opcode, int r1, int r2, int r3, long ext) {
        return ((ext & 0xFFFFFFFFL) << 32)
             | ((r3 & 0xFFL) << 24)
             | ((r2 & 0xFFL) << 16)
             | ((r1 & 0xFFL) << 8)
             | (opcode & 0xFFL);
    }

    // Convierte un archivo de ensamblador a binario (texto) en ProgramasBinarios con nombre adecuado
    public static String ensamblarArchivo(String rutaAsm) throws IOException {
        // Obtener nombre base del archivo sin extensión ni ruta y quitar "Ensamblador" si está presente
        File asmFile = new File(rutaAsm);
        String nombreBase = asmFile.getName();
        int punto = nombreBase.lastIndexOf('.');
        if (punto > 0) nombreBase = nombreBase.substring(0, punto);
        // Quitar sufijo "Ensamblador" si existe
        if (nombreBase.endsWith("Ensamblador")) {
            nombreBase = nombreBase.substring(0, nombreBase.length() - "Ensamblador".length());
        }

        // Crear ruta de salida relativa
        String dirSalida = "Lenguajes-de-Programacion/Computador/src/Main/ProgramasBinarios";
        String rutaBinTxt = dirSalida + File.separator + nombreBase + "Binario.txt";

        // Crear directorio si no existe
        File dir = new File(dirSalida);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> lineas = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(rutaAsm));
        try (PrintWriter txt = new PrintWriter(new FileWriter(rutaBinTxt))) {
            for (String linea : lineas) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#") || linea.startsWith("//")) continue;
                String[] partes = linea.split("\\s+|,");
                if (partes.length < 2) continue;
                int opcode = Integer.parseInt(partes[0]);
                int r1 = partes.length > 1 ? Integer.parseInt(partes[1]) : 0;
                int r2 = partes.length > 2 ? Integer.parseInt(partes[2]) : 0;
                int r3 = partes.length > 3 ? Integer.parseInt(partes[3]) : 0;
                long ext = partes.length > 4 ? Long.parseLong(partes[4]) : 0;
                long instruccion = instr(opcode, r1, r2, r3, ext);
                txt.printf("%064d%n", new java.math.BigInteger(Long.toBinaryString(instruccion)));
            }
        }
        return rutaBinTxt;
    }
}