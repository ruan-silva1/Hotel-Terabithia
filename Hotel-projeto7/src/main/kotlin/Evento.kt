package org.example
import kotlin.math.ceil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

class Evento(admin: Admin){
    var horario = 0
    var totalDeHorasEvento = 0
    val horarioFinal: Int
        get() = this.horario + this.totalDeHorasEvento


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
                println("${e[0]}/${e[1]}/${e[2]} das ${e[3]}:00 as ${e[4]}:00 no auditorio ${auditoriosReservados[i]}")
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
            println("evento registrado com sucesso! na data ${data[0]}/${data[1]}/${data[2]} das ${data[3]}:00 aa ${data[4]}:00")
            calcularPrecoAlimento(numeroConvidados)
            calcularPrecoFuncionarios(numeroConvidados,this.totalDeHorasEvento)
    }

    private fun escolherDataEvento(){
        println("Qual o mês para o evento? (1 a 12)")
        val mesEvento = Utils.lerEntradaNumerica()
        println("Qual o dia para o evento? (1 a 31)")
        val diaEvento = Utils.lerEntradaNumerica()
        val dataFormatada = LocalDate.parse("${diaEvento}/${mesEvento}/2025", DateTimeFormatter.ofPattern("d/M/yyyy"))
        var diaDaSemana = dataFormatada.dayOfWeek.toString()
        val ehFimDeSemana = if(diaDaSemana == "SUNDAY" || diaDaSemana == "SARTUDAY" ) true else false
        while (true) {
            when(ehFimDeSemana){
                true -> println("Qual o horário para início do evento? (8 a 15)")
                false-> println("Qual o horário para início do evento? (8 as 23)")
            }
            this.horario = Utils.lerEntradaNumerica()
            println("Quantas horas o evento irá durar?")
            this.totalDeHorasEvento = Utils.lerEntradaNumerica()
            val horarioValidoDiaNormal = this.horario in 8..23 && this.horarioFinal <= 23
            println(horarioValidoDiaNormal)
            val horarioValidoFimDeSemana = this.horario in 8..15 && this.horarioFinal <= 15
            println(horarioValidoFimDeSemana)
            if ((!ehFimDeSemana && !horarioValidoDiaNormal) || (ehFimDeSemana && !horarioValidoFimDeSemana)) {
                println("Horário inválido!")
            } else {
                break
            }
        }


        this.data.addAll(listOf(diaEvento, mesEvento, 2025, this.horario, this.horarioFinal))
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
            println("a quantidade de salgados necessarias é " + quantidadeDeSalgados + " unidades")
            println("O custo total de lanche para $quantidadeDeConvidados convidados é $custoTotal")
    }

    private fun calcularPrecoFuncionarios(quantidadeDeConvidados: Int,totalHorasEvento: Int){
        val custoGarcom = 10.50
        var quantidadeDeGarcons = ceil(numeroConvidados / 12.0).toInt()

        for (i in 0 until ceil(totalHorasEvento / 2.0).toInt()) {
            quantidadeDeGarcons++
        }

        val total = custoGarcom * quantidadeDeGarcons
        println("O valor total com funcionários é $total")
    }

}

