import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Biblioteca {
    private List<Tarefa> tarefas = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();

    public Biblioteca(Livro... livros) {
        this.livros = Arrays.asList(livros);
    }

    public void executarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
        tarefa.executar();
    }

    public void cancelarUltimaTarefa() {
        if (!tarefas.isEmpty()) {
            Tarefa tarefa = tarefas.get(tarefas.size() - 1);
            tarefa.cancelar();
            tarefas.remove(tarefa);
        }
    }
}
