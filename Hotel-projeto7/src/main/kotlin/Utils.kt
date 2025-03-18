package org.example

class Utils {
    companion object {
    fun lerEntradaNumerica(): Int {
        while (true) {
            try {
                return readln().toInt()
            } catch (e: NumberFormatException) {
                println("Entrada inválida! Por favor, insira um número válido.")
            }
        }
    }
}
}