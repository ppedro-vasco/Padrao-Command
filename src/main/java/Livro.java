import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private boolean situacao;
    private LocalDate dataPrevistaDevolucao;
    private double multa;

    public Livro(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public void iniciarEmprestimo() {
        this.situacao = true;
        // Definir a data prevista de devolução ao iniciar o empréstimo (por exemplo, 7 dias a partir da data atual)
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(7);
    }

    public void finalizarEmprestimo() {
        this.situacao = false;
        this.dataPrevistaDevolucao = null;
        this.multa = 0.0;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void aplicarMulta(double multa) {
        this.multa = multa;
    }

    public double getMulta() {
        return multa;
    }
}
