package org.example;

public abstract class DescontoHandler {
    private DescontoHandler proximo;

    public DescontoHandler(DescontoHandler proximo) {
        this.proximo = proximo;
    }

    public DescontoHandler getProximo() {
        return proximo;
    }

    public abstract String getCargoAprovador();
    public abstract boolean podeAprovar(float percentual);

    public String aprovarDesconto(float percentual) {
        if (podeAprovar(percentual)) {
            return getCargoAprovador();
        } else if (proximo != null) {
            return proximo.aprovarDesconto(percentual);
        } else {
            return "Sem aprovação";
        }
    }
}