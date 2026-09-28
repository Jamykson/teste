# Testes Funcionais - Aluguel de Carros

## Integrante

Jamykson Freitas

## Sobre o trabalho

Neste trabalho foram criados testes funcionais para a calculadora de custo de aluguel de carros fornecida na atividade.

Foram usadas três técnicas:

- Particionamento em classes de equivalência;
- Análise de valores-limite;
- Tabela de decisão para as regras de fidelidade.

A planilha que acompanha o projeto contém as partições, os limites, a tabela de decisão e os casos de teste com a rastreabilidade entre eles.

Os testes automatizados foram feitos com JUnit 5.

## Como executar

É necessário ter Java 17 ou superior e Maven instalados.

Na pasta do projeto, executar:

```bash
mvn test
```

Nem todos os testes passam. Isso foi mantido de propósito, pois alguns deles encontraram diferenças entre as regras descritas no enunciado e o comportamento da implementação fornecida.

Os valores esperados dos testes não foram alterados para fazer a implementação passar.

## Resultado da execução

Na execução final foram obtidos:

- 38 testes executados;
- 25 testes aprovados;
- 13 testes com falha;
- 0 erros;
- 0 testes ignorados.

As falhas encontradas estão principalmente nas regras de franquia de quilometragem, desconto por duração e fidelidade.

## Testes que falharam

### CT04 - Duas diárias sem desconto

Entradas principais: ECONOMICO, COMUM, 2 diárias, 200 km, sem atraso e sem seguro.

**Esperado:** R$ 240,00  
**Obtido:** R$ 320,00

Com duas diárias, a franquia deveria ser de 200 km. Como foram rodados exatamente 200 km, não deveria haver valor adicional.

Os R$ 80,00 a mais correspondem a 100 km cobrados a R$ 0,80/km. Isso indica que a implementação considerou somente 100 km de franquia.

### CT05 - Três diárias com desconto de 5%

Entradas principais: ECONOMICO, COMUM, 3 diárias, 300 km, sem atraso e sem seguro.

**Esperado:** R$ 342,00  
**Obtido:** R$ 542,00

Para três diárias:

`3 × 120 = 360`

Aplicando o desconto de 5%:

`360 × 0,95 = 342`

A franquia também deveria ser de 300 km, portanto não deveria haver cobrança de quilometragem.

A diferença de R$ 200,00 mostra que foram considerados 200 km excedentes. Novamente, o comportamento corresponde ao uso de uma franquia fixa de 100 km.

### CT06 - Seis diárias

Entradas principais: ECONOMICO, COMUM, 6 diárias e sem custos adicionais.

**Esperado:** R$ 684,00  
**Obtido:** R$ 720,00

O valor sem desconto é R$ 720,00. Como seis diárias pertencem à faixa de 5%, o resultado deveria ser:

`720 × 0,95 = 684`

O valor retornado foi o valor bruto, então o desconto não foi aplicado.

### CT07 - Cliente PRATA com 7 diárias

Entradas principais: ECONOMICO, PRATA, 7 diárias e sem atraso anterior.

**Esperado:** R$ 718,20  
**Obtido:** R$ 834,00

Pelo enunciado:

`7 × 120 = 840`

Desconto de duração:

`840 × 0,90 = 756`

Depois, fidelidade PRATA:

`756 × 0,95 = 718,20`

A implementação retornou R$ 834,00. Esse valor é R$ 6,00 menor que o valor bruto de R$ 840,00, e R$ 6,00 representa 5% de apenas uma diária de R$ 120,00.

Além do desconto de duração não ter sido aplicado corretamente, o desconto de fidelidade aparenta estar sendo calculado usando somente uma diária como base.

### CT08 - Quatorze diárias

Entradas principais: ECONOMICO, COMUM, 14 diárias e sem custos adicionais.

**Esperado:** R$ 1.512,00  
**Obtido:** R$ 1.680,00

Para 14 diárias, o desconto deveria ser de 10%.

`14 × 120 = 1.680`

`1.680 × 0,90 = 1.512`

O sistema retornou R$ 1.680,00, sem aplicar o desconto.

### CT09 - Quinze diárias

Entradas principais: ECONOMICO, COMUM, 15 diárias e sem custos adicionais.

**Esperado:** R$ 1.530,00  
**Obtido:** R$ 1.800,00

A partir de 15 diárias, a regra determina desconto de 15%.

`15 × 120 = 1.800`

`1.800 × 0,85 = 1.530`

Foi retornado o valor de R$ 1.800,00, ou seja, sem o desconto esperado.

### CT10 - Cliente PRATA com 6 diárias

Entradas principais: ECONOMICO, PRATA, 6 diárias e sem atraso anterior.

**Esperado:** R$ 684,00  
**Obtido:** R$ 720,00

Com seis diárias, o cliente PRATA ainda não atende ao mínimo de sete diárias necessário para o benefício de fidelidade.

Mesmo assim, ele deve receber o desconto normal de duração de 5%:

`720 × 0,95 = 684`

O resultado de R$ 720,00 mostra que esse desconto não foi aplicado.

### CT11 - Cliente PRATA com atraso anterior

Entradas principais: ECONOMICO, PRATA, 7 diárias e com atraso anterior.

**Esperado:** R$ 756,00  
**Obtido:** R$ 840,00

O atraso anterior retira o benefício de fidelidade, mas não interfere no desconto por duração.

Para sete diárias:

`840 × 0,90 = 756`

Como foram retornados R$ 840,00, o desconto de duração de 10% não foi aplicado.

### CT12 - Cliente OURO com 4 diárias

Entradas principais: ECONOMICO, OURO, 4 diárias e sem atraso anterior.

**Esperado:** R$ 456,00  
**Obtido:** R$ 432,00

Com quatro diárias, o cliente OURO ainda não alcança o mínimo de cinco diárias para fidelidade.

Deveria ser aplicado somente o desconto de duração de 5%:

`4 × 120 = 480`

`480 × 0,95 = 456`

O resultado foi R$ 432,00, que corresponde a 10% de desconto sobre R$ 480,00. Portanto, a implementação aplicou um percentual diferente do definido para essa faixa.

### CT13 - Cliente OURO com 5 diárias

Entradas principais: ECONOMICO, OURO, 5 diárias e sem atraso anterior.

**Esperado:** R$ 513,00  
**Obtido:** R$ 498,00

O cálculo definido pelas regras é:

`5 × 120 = 600`

Desconto de duração:

`600 × 0,95 = 570`

Fidelidade OURO:

`570 × 0,90 = 513`

O resultado foi R$ 498,00. Nesse caso, tanto o desconto usado para a duração quanto a forma de aplicar a fidelidade diferem do que foi definido no enunciado.

### CT14 - Cliente OURO com atraso anterior

Entradas principais: ECONOMICO, OURO, 5 diárias e com atraso anterior.

**Esperado:** R$ 570,00  
**Obtido:** R$ 510,00

Como existe atraso anterior, não deve existir desconto de fidelidade.

O cálculo deveria parar após o desconto de duração:

`600 × 0,95 = 570`

O valor de R$ 510,00 corresponde a um desconto de 15% sobre os R$ 600,00, diferente dos 5% definidos para cinco diárias.

### CT37 - Cliente PRATA com 8 diárias

Entradas principais: ECONOMICO, PRATA, 8 diárias e sem atraso anterior.

**Esperado:** R$ 820,80  
**Obtido:** R$ 954,00

O cálculo esperado é:

`8 × 120 = 960`

`960 × 0,90 = 864`

`864 × 0,95 = 820,80`

A implementação retornou R$ 954,00. A diferença entre R$ 960,00 e R$ 954,00 é R$ 6,00, novamente igual a 5% de uma única diária.

Esse caso também mostra que o desconto de duração não foi considerado corretamente antes da fidelidade.

### CT38 - Cliente OURO com 6 diárias

Entradas principais: ECONOMICO, OURO, 6 diárias e sem atraso anterior.

**Esperado:** R$ 615,60  
**Obtido:** R$ 708,00

O cálculo esperado é:

`6 × 120 = 720`

`720 × 0,95 = 684`

`684 × 0,90 = 615,60`

Foram retornados R$ 708,00. A diferença para o valor bruto é de R$ 12,00, exatamente 10% de uma diária de R$ 120,00.

Assim como no caso PRATA, o desconto de fidelidade aparenta estar usando o valor de uma diária como base.

## Resumo das falhas encontradas

Os testes mostraram três problemas principais na implementação.

O primeiro está na franquia de quilometragem. Nos testes com mais de uma diária, o cálculo se comportou como se a franquia fosse sempre de 100 km, embora a regra seja de 100 km por diária.

Também foram encontrados problemas nas faixas de desconto por duração. Alguns valores receberam percentuais diferentes do esperado e, em outras faixas, nenhum desconto foi aplicado.

Por último, os testes de fidelidade indicam que o percentual de PRATA ou OURO está sendo descontado do valor de uma diária, e não do valor das diárias depois do desconto de duração.

Os testes que encontraram esses problemas foram mantidos com os valores esperados definidos pelo enunciado.

## Planilha

O projeto contém uma planilha com:

- partições em classes de equivalência;
- análise de valores-limite;
- tabela de decisão para fidelidade;
- os 38 casos de teste;
- rastreabilidade dos casos para as partições, limites e regras utilizadas.

## Preparação da entrega

Depois de finalizar o relatório, executar:

```bash
mvn clean
```

Isso remove o diretório `target`.

O projeto deve ser compactado sem esse diretório, usando `.zip` ou `.tar.gz`. O nome do arquivo compactado deve ser igual ao `artifactId` definido no `pom.xml`.