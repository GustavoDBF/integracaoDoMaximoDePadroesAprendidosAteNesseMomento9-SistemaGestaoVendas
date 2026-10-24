package org.example;

public class FabricaProgramacao implements FabricaAbstrata {
    @Override
    public ItemCurso createCurso() {
        return new CursoProgramacao();
    }

    @Override
    public Certificado createCertificado() {
        return new CertificadoProgramacao();
    }
}