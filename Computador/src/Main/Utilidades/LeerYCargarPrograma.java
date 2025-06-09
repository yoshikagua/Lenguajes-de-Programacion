package Main.Utilidades;

import Main.Computador.Computer;
import java.util.List;

public class LeerYCargarPrograma {
    // Lee un archivo de texto binario y retorna las instrucciones como long[]
    public static long[] leerInstruccionesBinarias(String ruta) throws Exception {
        List<String> lineas = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(ruta));
        lineas.removeIf(l -> l.trim().isEmpty());
        long[] instrucciones = new long[lineas.size()];
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();
            if (linea.length() != 64 || !linea.matches("[01]+")) {
                throw new Exception("Formato de archivo inválido en la línea " + (i + 1) + ": " + linea);
            }
            instrucciones[i] = new java.math.BigInteger(linea, 2).longValue();
        }
        return instrucciones;
    }

    // Carga el programa en memoria y ejecuta
    public static void cargarPrograma(String ruta, int baseAddress, Computer computer) throws Exception {
        long[] instrucciones = leerInstruccionesBinarias(ruta);
        computer.loadProgram(instrucciones, baseAddress);
        computer.PC = baseAddress;
        computer.FLAGS = 0;
        computer.execute();
    }
}