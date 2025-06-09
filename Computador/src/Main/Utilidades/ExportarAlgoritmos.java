package Main.Utilidades;

import Main.Algoritmos.BusquedaBinaria;
import Main.Algoritmos.OrdenamientoInsercion;
import Main.Algoritmos.SumaArreglo;
import java.io.File;

public class ExportarAlgoritmos {

    // Exporta un algoritmo a ensamblador y binario, ajustando el nombre para el binario
    public static void exportarAlgoritmo(String nombre, long[] instrucciones, String dirAsm) throws Exception {
        String asmPath = dirAsm + nombre + "Ensamblador.asm";
        if (new File(asmPath).exists()) {
            System.out.println("Archivo ya existe y será sobreescrito: " + asmPath);
        }
        GeneradorEnsamblador.exportarPrograma(instrucciones, nombre);

        // Para el binario, quitar "Ensamblador" del nombre base si está presente
        String nombreBase = nombre;
        if (nombreBase.endsWith("Ensamblador")) {
            nombreBase = nombreBase.substring(0, nombreBase.length() - "Ensamblador".length());
        }
        String asmPathForBin = dirAsm + nombreBase + "Ensamblador.asm";
        GeneradorBinario.ensamblarArchivo(asmPathForBin);
    }

    public static void main(String[] args) {
        try {
            String dirAsm = "Lenguajes-de-Programacion/Computador/src/Main/ProgramasEnsambladores" + File.separator;

            exportarAlgoritmo("SumaArreglo", SumaArreglo.generarPrograma(), dirAsm);
            exportarAlgoritmo("OrdenamientoInsercion", OrdenamientoInsercion.generarPrograma(), dirAsm);
            exportarAlgoritmo("BusquedaBinaria", BusquedaBinaria.generarPrograma(11), dirAsm);

            System.out.println("Todos los algoritmos exportados a ensamblador y binario (sobrescribiendo si existían).");
        } catch (Exception e) {
            System.err.println("ERROR al exportar algoritmos: " + e.getMessage());
        }
    }
}