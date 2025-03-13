package org.example

fun main() {
    val user = User()
    iniciarMenu(user)
}

fun iniciarMenu(user: User) {
    while (true) {
        println(
            """Escolha uma das opções abaixo:
            |1- Fazer registro
            |2- Agendar diária
            |3- Sair
            """.trimMargin()
        )
        val escolhaMenu = lerEntradaNumerica()
        when (escolhaMenu) {
            1 -> user.registrarUser()
            2 -> {
                val hotel = Hotel(user)
                hotel.agendarDiaria()
            }
            3 -> {
                println("Saindo...")
                break
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}

fun lerEntradaNumerica(): Int {
    while (true) {
        try {
            return readln().toInt()
        } catch (e: NumberFormatException) {
            println("Entrada inválida! Por favor, insira um número válido.")
        }
    }
}

class User {
    var nome: String = ""
    var senhaValidada = false
    var diasHospedados = 0
    var quarto: Int = 0

    fun registrarUser() {
        println("Qual seu nome?")
        this.nome = readln()
        validarSenha()
        println("Usuário registrado com sucesso!")
    }

    private fun validarSenha() {
        while (true) {
            println("Qual a sua senha?")
            val senha = lerEntradaNumerica()
            if (senha == 2678) {
                this.senhaValidada = true
                break
            } else {
                println("Senha inválida. Tente novamente.")
            }
        }
    }
}

class Hotel(val user: User) {
    private val quartos = (1..20).toMutableList()
    private val nome = "Lyor"

    init {
        if (user.senhaValidada) {
            println("Bem-vindo ao hotel $nome, ${user.nome}! É um imenso prazer tê-lo por aqui!")
        } else {
            println("Usuário não registrado. Redirecionando para o registro...")
            user.registrarUser()
        }
    }

    fun agendarDiaria() {
        println("Quantos dias deseja ficar hospedado?")
        val qtdeDias = lerEntradaNumerica()

        if (qtdeDias <= 0 || qtdeDias >= 30) {
            println("Número de dias inválido. O número de dias deve ser entre 1 e 29.")
            return
        }

        user.diasHospedados = qtdeDias
        escolherQuarto()
        val valor = 300 * qtdeDias
        println("O valor total da estadia é R$$valor. Confirmar reserva? (S/N)")

        val confirmacao = readln().lowercase()
        if (confirmacao == "s") {
            println("Parabéns, ${user.nome}! Seu quarto foi agendado com sucesso. O número do quarto é ${user.quarto}.")
        } else {
            println("Reserva cancelada.")
        }
    }

    private fun escolherQuarto() {
        println("Escolha um dos quartos disponíveis:")
        for (quarto in quartos) {
            print("$quarto, ")
        }
        println()
        val numeroQuarto = lerEntradaNumerica()
        if (quartos.contains(numeroQuarto)) {
            user.quarto = numeroQuarto
            quartos.remove(numeroQuarto)
            println("Quarto $numeroQuarto reservado para você!")
        } else {
            println("Quarto indisponível. Por favor, escolha outro.")
            escolherQuarto()
        }
    }
}
