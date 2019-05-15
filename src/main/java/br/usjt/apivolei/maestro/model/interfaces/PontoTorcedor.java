package br.usjt.apivolei.maestro.model.interfaces;

import br.usjt.apivolei.maestro.model.bean.Torcedor;

public class PontoTorcedor implements IPonto {
    private Torcedor torcedor;

    public PontoTorcedor(Torcedor torcedor){
        this.torcedor = torcedor;
    }

    @Override
    public void incrementar(Double ponto) {
        torcedor.setPontos(torcedor.getPontos() + ponto.intValue());
    }

    @Override
    public void decrementar(Double ponto) {
        torcedor.setPontos(torcedor.getPontos() - ponto.intValue());
    }
}
