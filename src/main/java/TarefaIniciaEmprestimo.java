import java.util.ArrayList;
import java.util.List;

public class TarefaIniciaEmprestimo implements Tarefa {
    private List<Livro> livros = new ArrayList<>();

    public TarefaIniciaEmprestimo(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public void executar() {
        for (Livro livro : livros) {
            livro.iniciarEmprestimo();
        }
    }

    @Override
    public void cancelar() {
        for (Livro livro : livros) {
            livro.finalizarEmprestimo();
        }
    }
}
