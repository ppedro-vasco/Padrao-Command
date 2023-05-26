import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TarefaVerificarAtrasoDevolucao implements Tarefa {
    private List<Livro> livros;
    private LocalDate dataDevolucao;
    private double multaPorDia;

    public TarefaVerificarAtrasoDevolucao(List<Livro> livros, LocalDate dataDevolucao, double multaPorDia) {
        this.livros = livros;
        this.dataDevolucao = dataDevolucao;
        this.multaPorDia = multaPorDia;
    }

    @Override
    public void executar() {
        for (Livro livro : livros) {
            LocalDate dataPrevista = livro.getDataPrevistaDevolucao();
            long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataDevolucao);

            if (diasAtraso > 0) {
                double multa = diasAtraso * multaPorDia;
                livro.aplicarMulta(multa);
            }
        }
    }

    @Override
    public void cancelar() {
        // Não é necessário cancelamento para essa tarefa
    }
}
