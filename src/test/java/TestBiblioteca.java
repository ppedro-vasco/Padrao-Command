import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BibliotecaTest {

    Biblioteca biblioteca;
    Livro livro1;
    Livro livro2;

    @BeforeEach
    void setUp() {
        livro1 = new Livro(1, "A sociedade do anel");
        livro2 = new Livro(2, "As duas torres");
        biblioteca = new Biblioteca(livro1, livro2);
    }

    @Test
    void deveIniciarEmprestimoDeLivros() {
        TarefaIniciaEmprestimo tarefaEmprestimo = new TarefaIniciaEmprestimo(Arrays.asList(livro1, livro2));
        biblioteca.executarTarefa(tarefaEmprestimo);

        assertEquals(true, livro1.getSituacao());
        assertEquals(true, livro2.getSituacao());
    }

    @Test
    void deveFinalizarEmprestimoDeLivros() {
        TarefaFinalizaEmprestimo tarefaDevolucao = new TarefaFinalizaEmprestimo(Arrays.asList(livro1, livro2));
        biblioteca.executarTarefa(tarefaDevolucao);

        assertEquals(false, livro1.getSituacao());
        assertEquals(false, livro2.getSituacao());
    }

    @Test
    void deveCancelarUltimaTarefa() {
        TarefaIniciaEmprestimo tarefaEmprestimo = new TarefaIniciaEmprestimo(Arrays.asList(livro1, livro2));
        TarefaFinalizaEmprestimo tarefaDevolucao = new TarefaFinalizaEmprestimo(Arrays.asList(livro1, livro2));

        biblioteca.executarTarefa(tarefaEmprestimo);
        biblioteca.executarTarefa(tarefaDevolucao);

        biblioteca.cancelarUltimaTarefa();

        assertEquals(true, livro1.getSituacao());
        assertEquals(true, livro2.getSituacao());
    }

    @Test
    void deveVerificarAtrasoDevolucaoEAplicarMulta() {
        TarefaIniciaEmprestimo tarefaEmprestimo = new TarefaIniciaEmprestimo(Arrays.asList(livro1, livro2));
        biblioteca.executarTarefa(tarefaEmprestimo);

        LocalDate dataDevolucao = livro1.getDataPrevistaDevolucao().plusDays(2); //dias de atraso

        TarefaVerificarAtrasoDevolucao tarefaAtrasoDevolucao =
                new TarefaVerificarAtrasoDevolucao(Arrays.asList(livro1), dataDevolucao, 2.0);
        biblioteca.executarTarefa(tarefaAtrasoDevolucao);

        assertEquals(4.0, livro1.getMulta()); // Verificar se a multa foi aplicada corretamente
        assertEquals(0.0, livro2.getMulta()); // Verificar se não há multa no segundo livro
    }

}

