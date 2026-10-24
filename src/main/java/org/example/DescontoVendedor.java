package org.example;

public class DescontoVendedor extends DescontoHandler {
    public DescontoVendedor(DescontoHandler proximo) {
        super(proximo);
    }

    @Override
    public String getCargoAprovador() {
        return "Vendedor";
    }

    @Override
    public boolean podeAprovar(float percentual) {
        return percentual <= 10.0f;
    }
}