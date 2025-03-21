package org.example

class Evento(admin: Admin){
    val data = mutableListOf<Any>()
    val auditorio = mutableListOf<String>()
    val hotel = Hotel(admin)
    var numeroConvidados = 0

    fun cadastrarEvento() {
        while (true) {
            println("Qual o número de convidados para o evento?")
            numeroConvidados = Utils.lerEntradaNumerica()

            when {
                numeroConvidados < 10 || numeroConvidados > 350 -> {
                    println("Número inválido! Digite um número de 10 a 350.")
                }

                numeroConvidados in 10..219 -> {
                    if (numeroConvidados <= 150) {
                        println("Auditório Laranja será reservado.")
                        auditoriosReservados.add("Laranja")
                    } else {
                        val numeroCadeirasAdicionais = numeroConvidados - 150
                        println("Auditório Laranja será reservado, incluindo mais $numeroCadeirasAdicionais cadeiras adicionais.")
                        auditoriosReservados.add("Laranja com $numeroCadeirasAdicionais cadeiras adicionais")
                    }
                    break
                }

                numeroConvidados in 220..350 -> {
                    println("O Auditório Colorado será reservado.")
                    auditoriosReservados.add("Colorado")
                    break
                }
            }
        }
        if (datasDeEventos.isNotEmpty()) {
            println("Veja as datas já agendadas:")
            for ((i, e) in datasDeEventos.withIndex()) {
                println("${e[0]}/${e[1]}/${e[2]} as ${e[3]} horas no auditorio ${auditoriosReservados[i]}")
            }
        } else {
            println("Nenhuma data agendada.")
        }

        escolherDataEvento()
        for(e in datasDeEventos){
                if(e == data){
                    println("data ja escolhida! Escolha outro horario")
                    return
            }
        }

            datasDeEventos.add(data)
            auditoriosReservados.addAll(auditorio)
            println("evento registrado com sucesso! na data ${data[0]}/${data[1]}/${data[2]} as ${data[3]} horas")
            calcularPrecoAlimento(numeroConvidados)
    }

    private fun escolherDataEvento(){
        println("Qual o mês para o evento? (1 a 12)")
        val mesEvento = Utils.lerEntradaNumerica()
        println("Qual o dia para o evento? (1 a 31)")
        val diaEvento = Utils.lerEntradaNumerica()
        var horario = 0
        while (true) {
            println("Qual o horário para início do evento? (8 a 23)")
            horario = Utils.lerEntradaNumerica()
            if (horario !in 8..23) {
                println("Horário inválido!")
            } else {
                break
            }
        }
        data.addAll(listOf(diaEvento, mesEvento, 2025, horario))
    }

    private fun calcularPrecoAlimento(quantidadeDeConvidados: Int){ //funcao para calcular buffet do evento
            val quantidadeDeCafe = 0.2 * quantidadeDeConvidados
            val quantidadeDeAgua = 0.5 * quantidadeDeConvidados
            val quantidadeDeSalgados = 7.0 * quantidadeDeConvidados
            val custoCafe = 0.80 * quantidadeDeCafe
            val custoAgua = 0.40 * quantidadeDeAgua
            val custoSalgado = 0.34 * quantidadeDeSalgados
            val custoTotal = custoCafe + custoAgua + custoSalgado
            valorLanche.add(custoTotal)
            println("a quantidade de litros de cafe necessarias é " + quantidadeDeCafe + " litos")
            println("a quantidade de litros de agua necessarias é " + quantidadeDeAgua + " litros")
            println("a quantidade de salgados necessarias é " + quantidadeDeSalgados + " litros")
            println("O custo total de lanche para $quantidadeDeConvidados convidados é $custoTotal")
    }

}

