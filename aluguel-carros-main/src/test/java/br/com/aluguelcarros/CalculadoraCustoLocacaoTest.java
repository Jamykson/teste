package br.com.aluguelcarros;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraCustoLocacaoTest
{
    private final CalculadoraCustoLocacao calculadora =
            new CalculadoraCustoLocacao();

    @Test
    @DisplayName("CT01 - Locação básica sem custos adicionais")
    void ct01LocacaoBasicaSemCustosAdicionais()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                100,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("120.00"), resultado);
    }

    @Test
    @DisplayName("CT02 - Categoria INTERMEDIARIO")
    void ct02CategoriaIntermediario()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.INTERMEDIARIO,
                NivelCliente.COMUM,
                1,
                100,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("180.00"), resultado);
    }

    @Test
    @DisplayName("CT03 - Categoria SUV")
    void ct03CategoriaSuv()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.SUV,
                NivelCliente.COMUM,
                1,
                100,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("280.00"), resultado);
    }

    @Test
    @DisplayName("CT04 - Duas diárias sem desconto")
    void ct04DuasDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                2,
                200,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("240.00"), resultado);
    }

    @Test
    @DisplayName("CT05 - Três diárias com desconto de 5%")
    void ct05TresDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                3,
                300,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("342.00"), resultado);
    }

    @Test
    @DisplayName("CT06 - Seis diárias com desconto de 5%")
    void ct06SeisDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                6,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("684.00"), resultado);
    }

    @Test
    @DisplayName("CT07 - PRATA com 7 diárias e sem atraso anterior")
    void ct07PrataElegivel()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.PRATA,
                7,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("718.20"), resultado);
    }

    @Test
    @DisplayName("CT08 - Quatorze diárias com desconto de 10%")
    void ct08QuatorzeDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                14,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("1512.00"), resultado);
    }

    @Test
    @DisplayName("CT09 - Quinze diárias com desconto de 15%")
    void ct09QuinzeDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                15,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("1530.00"), resultado);
    }

    @Test
    @DisplayName("CT10 - PRATA com 6 diárias não recebe fidelidade")
    void ct10PrataSeisDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.PRATA,
                6,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("684.00"), resultado);
    }

    @Test
    @DisplayName("CT11 - PRATA com atraso anterior não recebe fidelidade")
    void ct11PrataComAtrasoAnterior()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.PRATA,
                7,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                true
        );

        assertEquals(new BigDecimal("756.00"), resultado);
    }

    @Test
    @DisplayName("CT12 - OURO com 4 diárias não recebe fidelidade")
    void ct12OuroQuatroDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.OURO,
                4,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("456.00"), resultado);
    }

    @Test
    @DisplayName("CT13 - OURO com 5 diárias recebe 10% de fidelidade")
    void ct13OuroElegivel()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.OURO,
                5,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("513.00"), resultado);
    }

    @Test
    @DisplayName("CT14 - OURO com atraso anterior não recebe fidelidade")
    void ct14OuroComAtrasoAnterior()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.OURO,
                5,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                true
        );

        assertEquals(new BigDecimal("570.00"), resultado);
    }

    @Test
    @DisplayName("CT15 - Exatamente no limite da franquia")
    void ct15LimiteFranquia()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                100,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("120.00"), resultado);
    }

    @Test
    @DisplayName("CT16 - Um quilômetro excedente")
    void ct16UmKmExcedente()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                101,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("120.80"), resultado);
    }

    @Test
    @DisplayName("CT17 - Noventa e nove quilômetros excedentes")
    void ct17NoventaNoveKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                199,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("199.20"), resultado);
    }

    @Test
    @DisplayName("CT18 - Cem quilômetros excedentes")
    void ct18CemKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                200,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("200.00"), resultado);
    }

    @Test
    @DisplayName("CT19 - Cento e um quilômetros excedentes")
    void ct19CentoUmKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                201,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("221.00"), resultado);
    }

    @Test
    @DisplayName("CT20 - Duzentos e noventa e nove quilômetros excedentes")
    void ct20DuzentosNoventaNoveKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                399,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("419.00"), resultado);
    }

    @Test
    @DisplayName("CT21 - Trezentos quilômetros excedentes")
    void ct21TrezentosKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                400,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("420.00"), resultado);
    }

    @Test
    @DisplayName("CT22 - Trezentos e um quilômetros excedentes")
    void ct22TrezentosUmKmExcedentes()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                401,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("571.50"), resultado);
    }

    @Test
    @DisplayName("CT23 - Uma hora de atraso não gera cobrança")
    void ct23UmaHoraAtraso()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                1,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("120.00"), resultado);
    }

    @Test
    @DisplayName("CT24 - Duas horas de atraso")
    void ct24DuasHorasAtraso()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                2,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("168.00"), resultado);
    }

    @Test
    @DisplayName("CT25 - Três horas de atraso")
    void ct25TresHorasAtraso()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                3,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("192.00"), resultado);
    }

    @Test
    @DisplayName("CT26 - Quatro horas de atraso gera uma diária adicional")
    void ct26QuatroHorasAtraso()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                4,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("240.00"), resultado);
    }

    @Test
    @DisplayName("CT27 - Seguro BASICO")
    void ct27SeguroBasico()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                0,
                TipoSeguro.BASICO,
                false
        );

        assertEquals(new BigDecimal("145.00"), resultado);
    }

    @Test
    @DisplayName("CT28 - Seguro COMPLETO")
    void ct28SeguroCompleto()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                0,
                TipoSeguro.COMPLETO,
                false
        );

        assertEquals(new BigDecimal("165.00"), resultado);
    }
    @Test
    @DisplayName("CT29 - Categoria nula deve lançar NullPointerException")
    void ct29CategoriaNula()
    {
        assertThrows(NullPointerException.class, () ->
                calculadora.calcularCustoLocacao(
                        null,
                        NivelCliente.COMUM,
                        1,
                        0,
                        0,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT30 - Nivel do cliente nulo deve lançar NullPointerException")
    void ct30NivelClienteNulo()
    {
        assertThrows(NullPointerException.class, () ->
                calculadora.calcularCustoLocacao(
                        CategoriaVeiculo.ECONOMICO,
                        null,
                        1,
                        0,
                        0,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT31 - Seguro nulo deve lançar NullPointerException")
    void ct31SeguroNulo()
    {
        assertThrows(NullPointerException.class, () ->
                calculadora.calcularCustoLocacao(
                        CategoriaVeiculo.ECONOMICO,
                        NivelCliente.COMUM,
                        1,
                        0,
                        0,
                        null,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT32 - Zero diarias deve lançar IllegalArgumentException")
    void ct32ZeroDiarias()
    {
        assertThrows(IllegalArgumentException.class, () ->
                calculadora.calcularCustoLocacao(
                        CategoriaVeiculo.ECONOMICO,
                        NivelCliente.COMUM,
                        0,
                        0,
                        0,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT33 - Quilometragem negativa deve lançar IllegalArgumentException")
    void ct33QuilometragemNegativa()
    {
        assertThrows(IllegalArgumentException.class, () ->
                calculadora.calcularCustoLocacao(
                        CategoriaVeiculo.ECONOMICO,
                        NivelCliente.COMUM,
                        1,
                        -1,
                        0,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT34 - Horas de atraso negativas devem lançar IllegalArgumentException")
    void ct34HorasAtrasoNegativas()
    {
        assertThrows(IllegalArgumentException.class, () ->
                calculadora.calcularCustoLocacao(
                        CategoriaVeiculo.ECONOMICO,
                        NivelCliente.COMUM,
                        1,
                        0,
                        -1,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }

    @Test
    @DisplayName("CT35 - NullPointerException deve prevalecer sobre entradas numericas invalidas")
    void ct35PrecedenciaNullPointerException()
    {
        assertThrows(NullPointerException.class, () ->
                calculadora.calcularCustoLocacao(
                        null,
                        NivelCliente.COMUM,
                        0,
                        -1,
                        -1,
                        TipoSeguro.SEM_SEGURO,
                        false
                )
        );
    }
    @Test
    @DisplayName("CT36 - COMUM com atraso anterior continua sem desconto de fidelidade")
    void ct36ComumComAtrasoAnterior()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.COMUM,
                1,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                true
        );

        assertEquals(new BigDecimal("120.00"), resultado);
    }

    @Test
    @DisplayName("CT37 - PRATA com 8 diárias permanece elegível à fidelidade")
    void ct37PrataOitoDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.PRATA,
                8,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("820.80"), resultado);
    }

    @Test
    @DisplayName("CT38 - OURO com 6 diárias permanece elegível à fidelidade")
    void ct38OuroSeisDiarias()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO,
                NivelCliente.OURO,
                6,
                0,
                0,
                TipoSeguro.SEM_SEGURO,
                false
        );

        assertEquals(new BigDecimal("615.60"), resultado);
    }
}