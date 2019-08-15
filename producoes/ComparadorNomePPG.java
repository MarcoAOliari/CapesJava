package producoes;
import java.util.Comparator;

import capes.PPG;

public class ComparadorNomePPG implements Comparator<PPG>{
	
	/**
	 * Metodo utilizado para comparar dois ppgs de acordo com o nome. A classe foi criada pois os ppgs sao ordenados
	 * de duas maneiras diferentes no programa completo (em dois comandos diferentes).
	 */
	@Override
	public int compare(PPG p1, PPG p2) {
		return p1.getNome().compareTo(p2.getNome());
	}
}
