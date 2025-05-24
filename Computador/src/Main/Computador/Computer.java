package Main.Computador;

public class Computer {
    public static final int MEMORY_SIZE = 1024 * 64; // 64 KB simulados (cada celda = 64 bits)
    public static final int WORD_SIZE = 8; // 8 bytes = 64 bits

    // Memoria principal
    public long[] memory = new long[MEMORY_SIZE];

    // Registros
    public long[] registers = new long[16]; // R0-R15
    public long ACC = 0;
    public long PC = 0;
    public long IR = 0;
    // FLAGS: 4 bits [Zero][Carry][Overflow][Sign]
    // Bit 0 (1): Zero, Bit 1 (2): Carry, Bit 2 (4): Overflow, Bit 3 (8): Sign
    public int FLAGS = 0;

    // ALU
    private ALU alu = new ALU();

    // Métodos de la CPU
    public void loadProgram(long[] program, int baseAddress) {
        int base = baseAddress / WORD_SIZE; // Divide entre 8 para obtener el índice correcto
        System.arraycopy(program, 0, memory, base, program.length);
        PC = baseAddress;
    }

    // Método para ejecutar el programa
    public void execute() {
        while (true) {
            System.out.printf("PC: %d | R1: %d | R2: %d | R3: %d | R4: %d | FLAGS: %d\n", PC, registers[1], registers[2], registers[3], registers[4], FLAGS);
            IR = memory[(int) (PC / WORD_SIZE)];
            if (!decodeAndExecute(IR)) break;
        }
    }

    // Método para establecer las banderas (flags)
    private void setFlags(long result) {
        int flags = 0;
        // Zero
        if (result == 0) flags |= 1;
        // Sign
        if (result < 0) flags |= 8;
        // Carry y Overflow no se calculan aquí por compatibilidad,
        FLAGS = flags;
    }

    // Método para ejecutar una instrucción
    public boolean step() {
        long instr = memory[(int)(PC / WORD_SIZE)];
        return decodeAndExecute(instr);
    }

    // Método para decodificar y ejecutar la instrucción
    private boolean decodeAndExecute(long instr) {
        int op = Instruction.getOpcode(instr);
        int r1 = Instruction.getReg1(instr);
        int r2 = Instruction.getReg2(instr);
        int r3 = Instruction.getReg3(instr);
        long ext = Instruction.getExtended(instr);

        // Imprimir la instrucción decodificada
        System.out.printf("PC: %d | Opcode: %d | r1: %d | r2: %d | r3: %d | ext: %d | FLAGS: %d\n", PC, op, r1, r2, r3, ext, FLAGS);

        switch (op) {
            // --- Sistema ---
            case Opcodes.HALT:
                return false;
            case Opcodes.NOP:
                PC += WORD_SIZE;
                break;

            // --- Aritméticas ---
            case Opcodes.SUMAR:
                if (r3 == 0 && ext != 0)
                    registers[r1] = alu.add(registers[r2], ext);
                else
                    registers[r1] = alu.add(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.RESTAR:
                if (r3 == 0 && ext != 0)
                    registers[r1] = alu.sub(registers[r2], ext);
                else
                    registers[r1] = alu.sub(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.MULTIPLICAR:
                if (r3 == 0 && ext != 0)
                    registers[r1] = alu.mul(registers[r2], ext);
                else
                    registers[r1] = alu.mul(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.DIVIDIR:
                if (r3 == 0 && ext != 0)
                    registers[r1] = alu.div(registers[r2], ext);
                else
                    registers[r1] = alu.div(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.INCREMENTAR:
                registers[r1] = alu.add(registers[r1], ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.DECREMENTAR:
                registers[r1] = alu.sub(registers[r1], ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.NEGAR:
                registers[r1] = alu.negar(registers[r1]);
                PC += WORD_SIZE;
                break;

            // --- Lógicas y de bits ---
            case Opcodes.AND:
                registers[r1] = alu.and(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.OR:
                registers[r1] = alu.or(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.XOR:
                registers[r1] = alu.xor(registers[r2], registers[r3]);
                PC += WORD_SIZE;
                break;
            case Opcodes.NOT:
                registers[r1] = alu.not(registers[r2]);
                PC += WORD_SIZE;
                break;
            case Opcodes.SHL:
                registers[r1] = alu.shl(registers[r2], (int)ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.SHR:
                registers[r1] = alu.shr(registers[r2], (int)ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.ROTAR_I:
                registers[r1] = alu.rotarIzq(registers[r2], (int)ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.ROTAR_D:
                registers[r1] = alu.rotarDer(registers[r2], (int)ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.BUSCAR_BIT:
                registers[r1] = alu.buscarBit(registers[r2]);
                PC += WORD_SIZE;
                break;
            case Opcodes.SET_BIT:
                registers[r1] = alu.setBit(registers[r1], (int)ext);
                PC += WORD_SIZE;
                break;
            case Opcodes.LIMPIAR_BIT:
                registers[r1] = alu.limpiarBit(registers[r1], (int)ext);
                PC += WORD_SIZE;
                break;

            // --- Memoria ---
            case Opcodes.CARGAR:
                registers[r1] = memory[(int)(registers[r2] / WORD_SIZE + registers[r3])];
                PC += WORD_SIZE;
                break;
            case Opcodes.GUARDAR:
                if (ext != 0)
                    memory[(int)(ext / WORD_SIZE)] = registers[r1];
                else
                    memory[(int)((registers[r2] + registers[r3] * WORD_SIZE) / WORD_SIZE)] = registers[r1];
                PC += WORD_SIZE;
                break;
            case Opcodes.PUSH:
                // Simula stack en memoria (ejemplo: decrementar SP y guardar)
                registers[15] -= WORD_SIZE;
                memory[(int)(registers[15] / WORD_SIZE)] = registers[r1];
                PC += WORD_SIZE;
                break;
            case Opcodes.POP:
                registers[r1] = memory[(int)(registers[15] / WORD_SIZE)];
                registers[15] += WORD_SIZE;
                PC += WORD_SIZE;
                break;
            case Opcodes.LEA:
                registers[r1] = registers[r2] + ext;
                PC += WORD_SIZE;
                break;
            case Opcodes.CLEAR:
                registers[r1] = alu.clear();
                PC += WORD_SIZE;
                break;
            case Opcodes.SWAP:
                alu.swap(registers, r1, r2);
                PC += WORD_SIZE;
                break;
            case Opcodes.CMP:
                setFlags(alu.cmp(registers[r1], registers[r2]));
                PC += WORD_SIZE;
                break;

            // --- Control de flujo ---
            case Opcodes.SALTAR:
                PC = ext;
                break;
            case Opcodes.SINZ:
                if ((FLAGS & 1) == 0) PC = ext; // Salta si Zero NO está activo
                else PC += WORD_SIZE;
                break;
            case Opcodes.SIZ:
                if ((FLAGS & 1) != 0) PC = ext; // Salta si Zero está activo
                else PC += WORD_SIZE;
                break;
            case Opcodes.JG:
                if (registers[r1] > registers[r2]) PC = ext;
                else PC += WORD_SIZE;
                break;
            case Opcodes.JGE:
                if (registers[r1] >= registers[r2]) PC = ext;
                else PC += WORD_SIZE;
                break;
            case Opcodes.JLE:
                if (registers[r1] <= registers[r2]) PC = ext;
                else PC += WORD_SIZE;
                break;
            case Opcodes.JL:
                if (registers[r1] < registers[r2]) PC = ext;
                else PC += WORD_SIZE;
                break;
            case Opcodes.CALL:
                // Guarda PC actual en stack y salta
                registers[15] -= WORD_SIZE;
                memory[(int)(registers[15] / WORD_SIZE)] = PC + WORD_SIZE;
                PC = ext;
                break;
            case Opcodes.RET:
                PC = memory[(int)(registers[15] / WORD_SIZE)];
                registers[15] += WORD_SIZE;
                break;

            // --- E/S (simulada) ---
            case Opcodes.IN:
                // Simula entrada: lee valor fijo o de consola si lo deseas
                registers[r1] = 0; // Cambia por lectura real si lo deseas
                PC += WORD_SIZE;
                break;
            case Opcodes.OUT:
                System.out.println("OUT: " + registers[r2]);
                PC += WORD_SIZE;
                break;

            // --- Interrupciones (simuladas) ---
            case Opcodes.CLI:
                // Deshabilita interrupciones (simulado)
                PC += WORD_SIZE;
                break;
            case Opcodes.STI:
                // Habilita interrupciones (simulado)
                PC += WORD_SIZE;
                break;

            // --- Extras ---
            case Opcodes.MOV:
                registers[r1] = registers[r2];
                PC += WORD_SIZE;
                break;
            case Opcodes.MOVI:
                registers[r1] = ext;
                PC += WORD_SIZE;
                break;

            default:
                System.err.println("Instrucción no válida: " + op);
                return false;
        }

        return true;
    }
}

