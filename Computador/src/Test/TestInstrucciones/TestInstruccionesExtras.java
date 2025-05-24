package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesExtras {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // SWAP
        comp = new Computer();
        long[] progSWAP = {
            Assembler.movi(1, 10),
            Assembler.movi(2, 20),
            Assembler.instr(Opcodes.SWAP, 1, 2, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSWAP, 0);
        comp.execute();
        ok = comp.registers[1] == 20 && comp.registers[2] == 10;
        System.out.println("SWAP: " + (ok ? "OK" : "FALLÓ") + "\n");

        // CLEAR
        comp = new Computer();
        long[] progCLEAR = {
            Assembler.movi(1, 99),
            Assembler.instr(Opcodes.CLEAR, 1, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progCLEAR, 0);
        comp.execute();
        ok = comp.registers[1] == 0;
        System.out.println("CLEAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // NOP
        comp = new Computer();
        long[] progNOP = {
            Assembler.instr(Opcodes.NOP, 0, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progNOP, 0);
        comp.execute();
        System.out.println("NOP: OK\n");

        // CLI: Simula deshabilitar interrupciones (solo debe ejecutarse sin error)
        comp = new Computer();
        long[] progCLI = {
            Assembler.instr(Opcodes.CLI, 0, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progCLI, 0);
        comp.execute();
        System.out.println("CLI: OK\n");

        // STI: Simula habilitar interrupciones (solo debe ejecutarse sin error)
        comp = new Computer();
        long[] progSTI = {
            Assembler.instr(Opcodes.STI, 0, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSTI, 0);
        comp.execute();
        System.out.println("STI: OK\n");

        // MOV: Copia el valor de un registro a otro
        comp = new Computer();
        long[] progMOV = {
            Assembler.movi(1, 123), // PC=0
            Assembler.instr(Opcodes.MOV, 2, 1, 0, 0), // PC=8
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=16
        };
        comp.loadProgram(progMOV, 0);
        comp.execute();
        ok = comp.registers[2] == 123;
        System.out.println("MOV: " + (ok ? "OK" : "FALLÓ") + "\n");

        // MOVI: Carga un valor inmediato en un registro
        comp = new Computer();
        long[] progMOVI = {
            Assembler.movi(3, 456), // PC=0
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=8
        };
        comp.loadProgram(progMOVI, 0);
        comp.execute();
        ok = comp.registers[3] == 456;
        System.out.println("MOVI: " + (ok ? "OK" : "FALLÓ") + "\n");

        // CMP: Compara dos registros y actualiza FLAGS
        comp = new Computer();
        long[] progCMP = {
            Assembler.movi(1, 10), // PC=0
            Assembler.movi(2, 10), // PC=8
            Assembler.instr(Opcodes.CMP, 1, 2, 0, 0), // PC=16
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=24
        };
        comp.loadProgram(progCMP, 0);
        comp.execute();
        ok = (comp.FLAGS & 1) != 0; // Zero activo si son iguales
        System.out.println("CMP: " + (ok ? "OK" : "FALLÓ") + "\n");

        // LEA: Carga en un registro la suma de otro registro y un inmediato
        comp = new Computer();
        long[] progLEA = {
            Assembler.movi(1, 100), // PC=0
            Assembler.instr(Opcodes.LEA, 2, 1, 0, 23), // PC=8
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=16
        };
        comp.loadProgram(progLEA, 0);
        comp.execute();
        ok = comp.registers[2] == 123;
        System.out.println("LEA: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}