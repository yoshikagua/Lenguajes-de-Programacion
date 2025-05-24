package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesAritmeticas {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // SUMAR
        comp = new Computer();
        long[] progSUMAR = {
            Assembler.movi(1, 7),
            Assembler.movi(2, 8),
            Assembler.instr(Opcodes.SUMAR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSUMAR, 0);
        comp.execute();
        ok = comp.registers[3] == 15;
        System.out.println("SUMAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // RESTAR
        comp = new Computer();
        long[] progRESTAR = {
            Assembler.movi(1, 10),
            Assembler.movi(2, 4),
            Assembler.instr(Opcodes.RESTAR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progRESTAR, 0);
        comp.execute();
        ok = comp.registers[3] == 6;
        System.out.println("RESTAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // MULTIPLICAR
        comp = new Computer();
        long[] progMUL = {
            Assembler.movi(1, 3),
            Assembler.movi(2, 5),
            Assembler.instr(Opcodes.MULTIPLICAR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progMUL, 0);
        comp.execute();
        ok = comp.registers[3] == 15;
        System.out.println("MULTIPLICAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // DIVIDIR
        comp = new Computer();
        long[] progDIV = {
            Assembler.movi(1, 20),
            Assembler.movi(2, 4),
            Assembler.instr(Opcodes.DIVIDIR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progDIV, 0);
        comp.execute();
        ok = comp.registers[3] == 5;
        System.out.println("DIVIDIR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // INCREMENTAR
        comp = new Computer();
        long[] progINC = {
            Assembler.movi(1, 10),
            Assembler.instr(Opcodes.INCREMENTAR, 1, 0, 0, 3),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progINC, 0);
        comp.execute();
        ok = comp.registers[1] == 13;
        System.out.println("INCREMENTAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // DECREMENTAR
        comp = new Computer();
        long[] progDEC = {
            Assembler.movi(1, 10),
            Assembler.instr(Opcodes.DECREMENTAR, 1, 0, 0, 4),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progDEC, 0);
        comp.execute();
        ok = comp.registers[1] == 6;
        System.out.println("DECREMENTAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // NEGAR
        comp = new Computer();
        long[] progNEG = {
            Assembler.movi(1, 5),
            Assembler.instr(Opcodes.NEGAR, 1, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progNEG, 0);
        comp.execute();
        ok = comp.registers[1] == -5;
        System.out.println("NEGAR: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}