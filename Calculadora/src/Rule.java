import java.util.ArrayList;

public class Rule {
	public ArrayList<Exp> reglas;
	public char id;
	
	public Rule(char id) {
		reglas = new ArrayList<Exp>();
		this.id = id;
	}
	
	public void add(String rule) {
		Token [] partes = new Token[rule.length()];
		int count = 0;
		for (int i = 0; i < rule.length(); i++) {
			partes[i] = new Token(rule.charAt(i),false);
			if(TerminalL.ispart(rule.charAt(i)))
				partes[i].terminal = true;
			else count++;
		}
		reglas.add(new Exp(partes,count));
	}
	
}
