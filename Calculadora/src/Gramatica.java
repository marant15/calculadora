import java.util.ArrayList;

public class Gramatica {
	public ArrayList<Rule> reglas;

	public Gramatica() {
		reglas = new ArrayList<Rule>();
	}

	public void addRule(Rule regla) {
		reglas.add(regla);
	}

	public boolean verficar(String cadena, char id) {
		if (cadena.length() < 1)
			return false;
		for (int i = 0; i < reglas.size(); i++) {
			if (reglas.get(i).id == id) {
				ArrayList<Exp> expresiones = reglas.get(i).reglas;
				for (int j = 0; j < expresiones.size(); j++) {
					ArrayList<ArrayList<Step>> nextsteps = separar(cadena, expresiones.get(j).tokens,
							expresiones.get(j).numeroNT);
					for (int k = 0; k < nextsteps.size(); k++) {
						if (nextsteps.get(0).get(0).cortef == -1) {
						} else if (nextsteps.get(0).get(0).cortef == -2) {
							return true;
						} else {
							boolean ver = true;
							for (int k2 = 0; k2 < nextsteps.get(k).size(); k2++) {
								boolean aux = verficar(cadena.substring(nextsteps.get(k).get(k2).cortei,
										nextsteps.get(k).get(k2).cortef), nextsteps.get(k).get(k2).sig);
								ver = ver && aux;
							}
							if (ver)
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	private ArrayList<ArrayList<Step>> separar(String cadena, Token[] tokens, int count) {
		ArrayList<ArrayList<Step>> nextsteps = new ArrayList<ArrayList<Step>>();
		if (count == 0) {
			nextsteps.add(new ArrayList<Step>());
			if (cadena.length() == tokens.length) {
				for (int i = 0; i < tokens.length; i++) {
					if (tokens[i].valor != cadena.charAt(i)) {
						nextsteps.get(0).add(new Step(-1, -1, 'x'));
						return nextsteps;
					}
				}
				nextsteps.get(0).add(new Step(-2, -2, 'x'));
				return nextsteps;
			} else {
				nextsteps.get(0).add(new Step(-1, -1, 'x'));
				return nextsteps;
			}
		} else {
			// verificar y devolver todas las combinaciones posibles
			boolean estadoi = true;
			boolean estadof = true;
			int counti = 0;
			int countf = 0;
			while (estadoi || estadof) {
				if (estadoi && tokens[counti].terminal) {
					if (cadena.charAt(counti) == tokens[counti].valor) {
						counti++;
					} else {
						nextsteps.add(new ArrayList<Step>());
						nextsteps.get(0).add(new Step(-1, -1, 'x'));
						return nextsteps;
					}
				} else
					estadoi = false;
				if (estadof && tokens[tokens.length - 1 - countf].terminal) {
					if (cadena.charAt(cadena.length() - 1 - countf) == tokens[tokens.length - 1 - countf].valor) {
						countf++;
					} else {
						nextsteps.add(new ArrayList<Step>());
						nextsteps.get(0).add(new Step(-1, -1, 'x'));
						return nextsteps;
					}
				} else
					estadof = false;
			}
			if (tokens.length - counti - countf == 3) {
				char pc = tokens[counti + 1].valor;
				String[] cadenas = cadena.split("\\" + pc);
				if (cadenas.length == 1) {
					nextsteps.add(new ArrayList<Step>());
					nextsteps.get(0).add(new Step(-1, -1, 'x'));
					return nextsteps;
				}
				for (int i = 0; i < cadenas.length - 1; i++) {
					nextsteps.add(new ArrayList<Step>());
					int posf = counti;
					for (int j = 0; j <= i; j++) {
						posf += cadenas[j].length() + j;
					}
					Step paso = new Step(counti, posf, tokens[counti].valor);
					nextsteps.get(i).add(paso);
					paso = new Step(posf + 1, cadena.length() - countf, tokens[counti + 2].valor);
					nextsteps.get(i).add(paso);
				}

			} else if (tokens.length - counti - countf == 2) {
				for (int i = 0; i < cadena.length() - 1; i++) {
					nextsteps.add(new ArrayList<Step>());
					int posf = counti + i + 1;
					Step paso = new Step(counti, posf, tokens[counti].valor);
					nextsteps.get(i).add(paso);
					paso = new Step(posf, cadena.length() - countf, tokens[counti + 1].valor);
					nextsteps.get(i).add(paso);
				}
			} else {
				nextsteps.add(new ArrayList<Step>());
				Step paso = new Step(counti, cadena.length() - countf, tokens[counti].valor);
				nextsteps.get(0).add(paso);
			}
		}
		return nextsteps;
	}
}
