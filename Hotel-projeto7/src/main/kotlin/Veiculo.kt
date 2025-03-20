package org.example

class Veiculo {
    fun calcularValorPosto() {
        var alcoolWayneOil: Double
        var gasolinaWayneOil: Double
        var alcoolStark: Double
        var gasolinaStark: Double
        var alcoolMaisBarato: Double
        var gasolinaMaisBarata: Double
        var empresa: String

        println("Qual o preço do litro do álcool no posto Wayne Oil?")
        alcoolWayneOil = readLine()!!.toDouble()

        println("Qual o preço do litro da gasolina no posto Wayne Oil?")
        gasolinaWayneOil = readLine()!!.toDouble()

        println("Qual o preço do litro do álcool Stark Petrol?")
        alcoolStark = readLine()!!.toDouble()

        println("Qual o preço do litro da gasolina no Stark Petrol?")
        gasolinaStark = readLine()!!.toDouble()

        // Comparando preços do álcool
        if (alcoolWayneOil <= alcoolStark) {
            alcoolMaisBarato = alcoolWayneOil
            empresa = "Wayne Oil"
        } else {
            alcoolMaisBarato = alcoolStark
            empresa = "Stark Petrol"
        }

        // Comparando preços da gasolina
        if (gasolinaWayneOil < gasolinaStark) {
            gasolinaMaisBarata = gasolinaWayneOil
            if (empresa == "Stark Petrol") empresa = "Wayne Oil"
        } else {
            gasolinaMaisBarata = gasolinaStark
            if (empresa == "Wayne Oil") empresa = "Stark Petrol"
        }

        // Verificando se o álcool vale mais a pena
        if (alcoolMaisBarato < (gasolinaWayneOil * 0.7) && alcoolMaisBarato < (gasolinaStark * 0.7)) {
            println("O álcool da empresa $empresa custa $alcoolMaisBarato e ele é o que vale mais a pena.")
        } else {
            println("A gasolina da empresa $empresa custa $gasolinaMaisBarata e ela é a que vale mais a pena.")
        }
    }
}