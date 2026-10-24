package org.example;

public class PedidoEstadoPago extends PedidoEstado {
    private PedidoEstadoPago() {}
    private static PedidoEstadoPago instance = new PedidoEstadoPago();
    public static PedidoEstadoPago getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Pago";
    }
}