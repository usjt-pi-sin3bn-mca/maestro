package br.usjt.apivolei.maestro.model.interfaces;

public class PontoTorcedorImpl implements IPontoTorcedor {
    private Integer pontuacaoAtual;

    public PontoTorcedorImpl(Integer pontos) {
        pontuacaoAtual = pontos;
    }

    @Override
    public Integer incrementar(Integer pontoIncrementar) {
        pontuacaoAtual += pontoIncrementar;

        return pontuacaoAtual;
    }

    @Override
    public Integer decrementar(Integer pontoDecrementar) {
        pontuacaoAtual -= pontoDecrementar;

        return pontuacaoAtual;
    }
}
