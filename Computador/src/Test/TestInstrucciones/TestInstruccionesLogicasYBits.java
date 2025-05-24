package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesLogicasYBits {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // AND
        comp = new Computer();
        long[] progAND = {
            Assembler.movi(1, 6), // 110b
            Assembler.movi(2, 5), // 101b
            Assembler.instr(Opcodes.AND, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progAND, 0);
        comp.execute();
        ok = comp.registers[3] == 4; // 100b
        System.out.println("AND: " + (ok ? "OK" : "FALLÓ") + "\n");

        // OR
        comp = new Computer();
        long[] progOR = {
            Assembler.movi(1, 6),
            Assembler.movi(2, 5),
            Assembler.instr(Opcodes.OR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progOR, 0);
        comp.execute();
        ok = comp.registers[3] == 7;
        System.out.println("OR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // XOR
        comp = new Computer();
        long[] progXOR = {
            Assembler.movi(1, 6),
            Assembler.movi(2, 5),
            Assembler.instr(Opcodes.XOR, 3, 1, 2, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progXOR, 0);
        comp.execute();
        ok = comp.registers[3] == 3;
        System.out.println("XOR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // NOT
        comp = new Computer();
        long[] progNOT = {
            Assembler.movi(1, 0),
            Assembler.instr(Opcodes.NOT, 2, 1, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progNOT, 0);
        comp.execute();
        ok = comp.registers[2] == ~0L;
        System.out.println("NOT: " + (ok ? "OK" : "FALLÓ") + "\n");

        // SHL
        comp = new Computer();
        long[] progSHL = {
            Assembler.movi(1, 2),
            Assembler.instr(Opcodes.SHL, 2, 1, 0, 3),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSHL, 0);
        comp.execute();
        ok = comp.registers[2] == 16;
        System.out.println("SHL: " + (ok ? "OK" : "FALLÓ") + "\n");

        // SHR
        comp = new Computer();
        long[] progSHR = {
            Assembler.movi(1, 16),
            Assembler.instr(Opcodes.SHR, 2, 1, 0, 2),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSHR, 0);
        comp.execute();
        ok = comp.registers[2] == 4;
        System.out.println("SHR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // ROTAR_I
        comp = new Computer();
        long[] progROTAR_I = {
            Assembler.movi(1, 0b0001),
            Assembler.instr(Opcodes.ROTAR_I, 2, 1, 0, 2),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progROTAR_I, 0);
        comp.execute();
        ok = comp.registers[2] == 0b0100;
        System.out.println("ROTAR_I: " + (ok ? "OK" : "FALLÓ") + "\n");

        // ROTAR_D
        comp = new Computer();
        long[] progROTAR_D = {
            Assembler.movi(1, 0b1000),
            Assembler.instr(Opcodes.ROTAR_D, 2, 1, 0, 2),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progROTAR_D, 0);
        comp.execute();
        ok = comp.registers[2] == 0b0010;
        System.out.println("ROTAR_D: " + (ok ? "OK" : "FALLÓ") + "\n");

        // SET_BIT
        comp = new Computer();
        long[] progSETBIT = {
            Assembler.movi(1, 0b0000),
            Assembler.instr(Opcodes.SET_BIT, 1, 0, 0, 2),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSETBIT, 0);
        comp.execute();
        ok = comp.registers[1] == 0b0100;
        System.out.println("SET_BIT: " + (ok ? "OK" : "FALLÓ") + "\n");

        // LIMPIAR_BIT
        comp = new Computer();
        long[] progLIMPIARBIT = {
            Assembler.movi(1, 0b0111),
            Assembler.instr(Opcodes.LIMPIAR_BIT, 1, 0, 0, 1),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progLIMPIARBIT, 0);
        comp.execute();
        ok = comp.registers[1] == 0b0101;
        System.out.println("LIMPIAR_BIT: " + (ok ? "OK" : "FALLÓ") + "\n");

        // BUSCAR_BIT
        comp = new Computer();
        long[] progBUSCARBIT = {
            Assembler.movi(1, 0b0100),
            Assembler.instr(Opcodes.BUSCAR_BIT, 2, 1, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progBUSCARBIT, 0);
        comp.execute();
        ok = comp.registers[2] == 2;
        System.out.println("BUSCAR_BIT: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}