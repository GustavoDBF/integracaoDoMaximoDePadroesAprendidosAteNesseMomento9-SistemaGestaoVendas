package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaVendasTest {

    // --- Singleton & Factory Method ---

    @Test
    void deveRetornarMesmaInstanciaSingleton() {
        GerenciadorVendas g1 = GerenciadorVendas.getInstance();
        GerenciadorVendas g2 = GerenciadorVendas.getInstance();
        assertEquals(g1, g2);
    }

    @Test
    void deveObterFabricaComSucesso() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        assertNotNull(fabrica);
    }

    @Test
    void deveRetornarExcecaoParaFabricaInexistente() {
        try {
            GerenciadorVendas.getInstance().obterFabrica("Inexistente");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Fabrica inexistente", e.getMessage());
        }
    }

    // --- Bridge ---

    @Test
    void deveEmitirCertificadoComFormatoPDF() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        Certificado certificado = fabrica.createCertificado();
        certificado.setFormato(new FormatoPDF());

        assertEquals("Certificado Programacao no formato PDF", certificado.emitir());
    }

    @Test
    void deveRetornarExcecaoAoEmitirCertificadoSemFormato() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        Certificado certificado = fabrica.createCertificado();

        try {
            certificado.emitir();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Formato não definido", e.getMessage());
        }
    }

    // --- Template Method ---

    @Test
    void deveRetornarRelatorioFinalAprovado() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();

        assertEquals("Aluno no curso Programacao: Aprovado", curso.getRelatorioFinal(7.0f));
    }

    @Test
    void deveRetornarRelatorioFinalReprovado() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();

        assertEquals("Aluno no curso Programacao: Reprovado", curso.getRelatorioFinal(6.9f));
    }

    // --- Decorator ---

    @Test
    void deveCalcularPrecoCursoSemDecorator() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();

        assertEquals(1000.0f, curso.getPreco());
    }

    @Test
    void deveCalcularPrecoCursoComMentoria() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        ItemCurso cursoComMentoria = new MentoriaAdicional(curso);

        assertEquals(1300.0f, cursoComMentoria.getPreco());
    }

    // --- State & Observer ---

    @Test
    void devePagarPedidoENotificarCliente() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        Pedido pedido = new Pedido(curso);

        Cliente cliente = new Cliente("Lucas");
        cliente.acompanharPedido(pedido);

        assertTrue(pedido.pagar());
        assertEquals(PedidoEstadoPago.getInstance(), pedido.getEstado());
        assertEquals("Lucas, status atualizado para Pago", cliente.getUltimaNotificacao());
    }

    @Test
    void deveCancelarPedidoENotificarCliente() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        Pedido pedido = new Pedido(curso);

        Cliente cliente = new Cliente("Lucas");
        cliente.acompanharPedido(pedido);

        assertTrue(pedido.cancelar());
        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
        assertEquals("Lucas, status atualizado para Cancelado", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteQueNaoAcompanhaPedido() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        Pedido pedido = new Pedido(curso);

        Cliente cliente = new Cliente("Lucas");
        pedido.pagar();

        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void naoDevePagarPedidoJaPago() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        Pedido pedido = new Pedido(curso);

        pedido.pagar();
        assertFalse(pedido.pagar());
    }

    @Test
    void naoDeveCancelarPedidoPago() {
        FabricaAbstrata fabrica = GerenciadorVendas.getInstance().obterFabrica("Programacao");
        ItemCurso curso = fabrica.createCurso();
        Pedido pedido = new Pedido(curso);

        pedido.pagar();
        assertFalse(pedido.cancelar());
    }

    // --- Chain of Responsibility ---

    @Test
    void deveAprovarDescontoComVendedor() {
        DescontoGerente gerente = new DescontoGerente(null);
        DescontoVendedor vendedor = new DescontoVendedor(gerente);

        assertEquals("Vendedor", vendedor.aprovarDesconto(10.0f));
    }

    @Test
    void deveAprovarDescontoComGerente() {
        DescontoGerente gerente = new DescontoGerente(null);
        DescontoVendedor vendedor = new DescontoVendedor(gerente);

        assertEquals("Gerente", vendedor.aprovarDesconto(25.0f));
    }

    @Test
    void deveRetornarSemAprovacaoParaDescontoExcessivo() {
        DescontoGerente gerente = new DescontoGerente(null);
        DescontoVendedor vendedor = new DescontoVendedor(gerente);

        assertEquals("Sem aprovação", vendedor.aprovarDesconto(25.1f));
    }
}