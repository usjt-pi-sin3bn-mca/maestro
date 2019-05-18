package br.usjt.apivolei.maestro.model.interfaces;

public class PontoTorcedor implements IPonto {
    private Double pontuacaoAtual;

    public PontoTorcedor(Double pontos) {
        pontuacaoAtual = pontos;
    }

    @Override
    public Double incrementar(Double pontoIncrementar) {
        pontuacaoAtual += pontoIncrementar;

        return pontuacaoAtual;
    }

    @Override
    public Double decrementar(Double pontoDecrementar) {
        pontuacaoAtual -= pontoDecrementar;

        return pontuacaoAtual;
    }
}
