package org.example;

public class CertificadoProgramacao extends Certificado {
    @Override
    public String emitir() {
        if (this.formato == null) {
            throw new IllegalArgumentException("Formato não definido");
        }
        return "Certificado Programacao no formato " + this.formato.gerar();
    }
}