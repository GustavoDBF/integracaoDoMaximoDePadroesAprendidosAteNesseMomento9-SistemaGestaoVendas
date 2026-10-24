package org.example;

public abstract class Certificado {
    protected Formato formato;

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public abstract String emitir();
}