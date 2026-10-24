package org.example;

public class CursoProgramacao extends ItemCurso {
    public CursoProgramacao() {
        super("Programacao", 1000.0f);
    }

    @Override
    public boolean verificarAprovacao(float nota) {
        return nota >= 7.0f;
    }
}