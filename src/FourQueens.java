import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class FourQueens {
    public static void main(String[] args) {
        int n = 4;

        Model model = new Model("4 reines");

        // Définir les variables : une reine par colonne
        IntVar[] queens = new IntVar[n];
        for (int i = 1; i < 5; i++) {
            queens[i] = model.intVar("Q" + i, 1, 4);
        }




        // Contraintes :
        // 1. Toutes les reines doivent être sur des lignes différentes
        model.allDifferent(queens).post();

        // 2. Pas d'attaques diagonales
        for (int i = 1; i < n+1; i++) {
            for (int j = i + 1; j < n+1; j++) {
                model.arithm(queens[i], "!=", queens[j], "+", j - i).post(); // Diagonale ascendante
                model.arithm(queens[i], "!=", queens[j], "-", j - i).post(); // Diagonale descendante

            }
        }

        if (model.getSolver().solve()) {
            System.out.println("Solution trouvée :");
            for (int i = 1; i < n+1; i++) {
                System.out.println("Reine " + i + " : Ligne " + queens[i].getValue());
            }
        } else {
            System.out.println("Pas de solution trouvée.");
        }
    }
}
