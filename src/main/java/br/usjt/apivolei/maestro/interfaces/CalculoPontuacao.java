package br.usjt.apivolei.maestro.interfaces;

public class CalculoPontuacao implements IPonto {
    private Double pontuacaoAtual;

    public CalculoPontuacao(Double pontuacaoAtual) {
        this.pontuacaoAtual = pontuacaoAtual;
    }

    @Override
    public Double incrementar(Double valorIncrementar) {
        pontuacaoAtual += valorIncrementar;

        return pontuacaoAtual;
    }

    @Override
    public Double decrementar(Double valorDecrementar) {
        pontuacaoAtual -= valorDecrementar;

        return pontuacaoAtual;
    }
}
