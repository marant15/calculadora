import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TerminalL.setTerminal();
		TerminalL.add('+');
		TerminalL.add('*');
		TerminalL.add('-');
		TerminalL.add('/');
		TerminalL.add('^');
		TerminalL.add(')');
		TerminalL.add('(');
		TerminalL.add('.');
		TerminalL.add('0');
		TerminalL.add('1');
		TerminalL.add('2');
		TerminalL.add('3');
		TerminalL.add('4');
		TerminalL.add('5');
		TerminalL.add('6');
		TerminalL.add('7');
		TerminalL.add('8');
		TerminalL.add('9');
		Gramatica g = new Gramatica();
		Rule regla = new Rule('L');
		regla.add("L+L");
		regla.add("L-L");
	regla.add("S");
		g.addRule(regla);
		regla = new Rule('S');
		regla.add("S/S");
		regla.add("S*S");
		regla.add("S^S");
		regla.add("(L)");
		regla.add("Z");
		regla.add("+S");
		regla.add("-S");
		//regla.add("S(L)");
		g.addRule(regla);
		regla = new Rule('Z');
		regla.add("N");
		regla.add("N.N");
		g.addRule(regla);
		regla = new Rule('N');
		regla.add("NT");
		regla.add("T");
		g.addRule(regla);
		regla = new Rule('T');
		regla.add("0");
		regla.add("1");
		regla.add("2");
		regla.add("3");
		regla.add("4");
		regla.add("5");
		regla.add("6");
		regla.add("7");
		regla.add("8");
		regla.add("9");
		g.addRule(regla);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				System.out.println(g.verficar(line, 'L'));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(g.verficar("(9.3+5.8)+(8)", 'L'));
		
		
	}

}
