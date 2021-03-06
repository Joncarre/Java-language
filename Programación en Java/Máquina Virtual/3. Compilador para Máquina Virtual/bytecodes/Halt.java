package bytecodes;

import cpu.CPU;

public class Halt implements ByteCode {
	
	@Override
	public void execute(CPU cpu) {
		cpu.halt();
	}
	@Override
	public ByteCode parse(String[] s) {
		if(s.length == 1 && s[0].equalsIgnoreCase(("HALT")))
			return new Halt();
		else
			return null;
	}
	/**
	 * Imprime "HALT"
	 */
	public String toString(){
		return "HALT";
	}
}
