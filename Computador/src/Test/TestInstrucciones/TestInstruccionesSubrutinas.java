package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesSubrutinas {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // CALL: Salta a subrutina (no regresa)
        comp = new Computer();
        comp.registers[15] = Computer.MEMORY_SIZE * Computer.WORD_SIZE; // Inicializa SP
        long[] progCALL = {
            Assembler.instr(Opcodes.CALL, 0, 0, 0, 24), // Llama a subrutina en PC=24
            Assembler.movi(1, 0), // PC=8 (debe saltarse)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=16
            // Subrutina (PC=24)
            Assembler.movi(1, 55), // PC=24
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=32
        };
        comp.loadProgram(progCALL, 0);
        comp.execute();
        ok = comp.registers[1] == 55;
        System.out.println("CALL: " + (ok ? "OK" : "FALLÓ") + "\n");

        // RET: Salta a una dirección guardada en el stack (simulación)
        comp = new Computer();
        comp.PC = 24;
        comp.registers[15] = (Computer.MEMORY_SIZE - 1) * Computer.WORD_SIZE;
        comp.memory[(int)(comp.registers[15] / Computer.WORD_SIZE)] = 8; // Dirección de retorno
        long[] progRET = {
            Assembler.movi(1, 77), // PC=0
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=8 (HALT en la dirección de retorno)
            Assembler.movi(1, 99), // PC=16 (debe saltarse)
            Assembler.instr(Opcodes.RET, 0, 0, 0, 0) // PC=24 (simula estar en subrutina)
        };
        comp.loadProgram(progRET, 0);
        comp.execute();
        ok = comp.registers[1] == 77;
        System.out.println("RET: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}