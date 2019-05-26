package br.usjt.apivolei.maestro.model.interfaces;

public class PontoTorcedorImpl implements IPontoTorcedor {
    private Double pontuacaoAtual;

    public PontoTorcedorImpl(Double pontos) {
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
