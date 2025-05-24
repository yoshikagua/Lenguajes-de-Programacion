package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesMemoria {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // CARGAR
        comp = new Computer();
        comp.memory[0x2000 / Computer.WORD_SIZE] = 99;
        long[] progCARGAR = {
            Assembler.movi(1, 0x2000),
            Assembler.instr(Opcodes.CARGAR, 2, 1, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progCARGAR, 0);
        comp.execute();
        ok = comp.registers[2] == 99;
        System.out.println("CARGAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // GUARDAR
        comp = new Computer();
        long[] progGUARDAR = {
            Assembler.movi(1, 123),
            Assembler.instr(Opcodes.GUARDAR, 1, 0, 0, 0x3000),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progGUARDAR, 0);
        comp.execute();
        ok = comp.memory[0x3000 / Computer.WORD_SIZE] == 123;
        System.out.println("GUARDAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // PUSH y POP
        comp = new Computer();
        comp.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE; // Inicializa SP
        long[] progPUSHPOP = {
            Assembler.movi(1, 55),
            Assembler.instr(Opcodes.PUSH, 1, 0, 0, 0),
            Assembler.instr(Opcodes.POP, 2, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progPUSHPOP, 0);
        comp.execute();
        ok = comp.registers[2] == 55;
        System.out.println("PUSH/POP: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}