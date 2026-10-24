package org.example;

public abstract class ItemCursoDecorator extends ItemCurso {
    private ItemCurso itemCurso;

    public ItemCursoDecorator(ItemCurso itemCurso) {
        this.itemCurso = itemCurso;
    }

    public ItemCurso getItemCurso() {
        return itemCurso;
    }

    public abstract float getValorAdicional();

    @Override
    public float getPreco() {
        return this.itemCurso.getPreco() + getValorAdicional();
    }

    @Override
    public boolean verificarAprovacao(float nota) {
        return this.itemCurso.verificarAprovacao(nota);
    }
}