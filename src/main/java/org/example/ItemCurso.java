package org.example;

public abstract class ItemCurso {
    private String nome;
    private float precoBase;

    public ItemCurso() {}

    public ItemCurso(String nome, float precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public float getPreco() {
        return precoBase;
    }

    public abstract boolean verificarAprovacao(float nota);

    // Template Method
    public String getRelatorioFinal(float nota) {
        String resultado = verificarAprovacao(nota) ? "Aprovado" : "Reprovado";
        return "Aluno no curso " + nome + ": " + resultado;
    }

    public String getNome() {
        return nome;
    }
}