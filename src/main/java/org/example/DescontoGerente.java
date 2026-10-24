package org.example;

public class DescontoGerente extends DescontoHandler {
    public DescontoGerente(DescontoHandler proximo) {
        super(proximo);
    }

    @Override
    public String getCargoAprovador() {
        return "Gerente";
    }

    @Override
    public boolean podeAprovar(float percentual) {
        return percentual <= 25.0f;
    }
}