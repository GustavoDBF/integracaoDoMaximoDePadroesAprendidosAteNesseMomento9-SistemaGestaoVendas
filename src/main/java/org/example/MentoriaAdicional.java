package org.example;

public class MentoriaAdicional extends ItemCursoDecorator {
    public MentoriaAdicional(ItemCurso itemCurso) {
        super(itemCurso);
    }

    @Override
    public float getValorAdicional() {
        return 300.0f;
    }
}