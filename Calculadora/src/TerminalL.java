import java.util.ArrayList;

public final class TerminalL {
	private static ArrayList<Character> terminales;
	private TerminalL() {
		terminales = new ArrayList<Character>();
	}
	
	public static void setL(ArrayList<Character> newl) {
		terminales = newl;
	}
	
	public static boolean ispart(char caracter) {
		for (int i = 0; i < terminales.size(); i++) {
			if(terminales.get(i) == caracter) return true;
		}
		return false;
	}
	
	public static void add(char caracter) {
		terminales.add(caracter);
	}
	
	public static void setTerminal() {
		if(terminales == null) new TerminalL();
	}
}
