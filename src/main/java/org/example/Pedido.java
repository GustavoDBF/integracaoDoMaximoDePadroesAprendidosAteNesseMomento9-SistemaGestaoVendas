package org.example;

import java.util.Observable;

public class Pedido extends Observable {
    private ItemCurso itemCurso;
    private PedidoEstado estado = PedidoEstadoNovo.getInstance();

    public Pedido(ItemCurso itemCurso) {
        this.itemCurso = itemCurso;
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        atualizarStatus();
    }

    public boolean pagar() {
        return estado.pagar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public void atualizarStatus() {
        setChanged();
        notifyObservers();
    }

    public float getValorTotal() {
        return itemCurso.getPreco();
    }
}