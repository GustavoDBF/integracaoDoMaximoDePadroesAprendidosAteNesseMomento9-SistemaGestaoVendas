package org.example;

public class GerenciadorVendas {

    private GerenciadorVendas() {}

    private static GerenciadorVendas instance = new GerenciadorVendas();

    public static GerenciadorVendas getInstance() {
        return instance;
    }

    public FabricaAbstrata obterFabrica(String nomeFabrica) {
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName("org.example.Fabrica" + nomeFabrica);
            objeto = classe.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Fabrica inexistente");
        }
        if (!(objeto instanceof FabricaAbstrata)) {
            throw new IllegalArgumentException("Fabrica invalida");
        }
        return (FabricaAbstrata) objeto;
    }
}