package org.example
val usuarios: MutableList<List<String>> = mutableListOf()
val quartosDisponiveis = (1..20).toMutableList()
val quartosUsuarios = mutableListOf<Int>()

fun main() {
    val user = Admin()
    iniciarMenu(user)
}

fun iniciarMenu(admin: Admin) {
    while (true) {
        println(
            """Escolha uma das opções abaixo:
            |1- Fazer registro
            |2- Agendar diária
            |3- Sair
            |4- mostrar hospedes
            |5- pesquisar hospedes
            """.trimMargin()
        )
        val escolhaMenu = lerEntradaNumerica()
        when (escolhaMenu) {
            1 -> admin.loginAdmin()
            2 -> {
                val hotel = Hotel(admin)
                hotel.agendarDiaria()
            }
            3 -> {
                println("Saindo...")
                break
            }
            4-> {
                val hotel = Hotel(admin)
                hotel.mostrarHospedes()
                }
            5->{
                val hotel = Hotel(admin)
                hotel.pesquisarHospedes()
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

class User{
    var quartoAgendado = 0
    var qtdeDias = 0
}

class Admin {
    var nome: String = ""
    var senhaValidada = false

    fun loginAdmin() {
        println("Qual nome do admin")
        this.nome = readln()
        validarSenha()
        println("admin logado com sucesso!")
    }

    private fun validarSenha() {
        while (true) {
            println("Qual a senha?")
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


class Hotel(val admin: Admin) {
    var user = User()
    val hospedes = mutableListOf<String>()
    private val nome = "Lyor"
    var gratuitates = 0
    var meias = 0
    var completas = 0
    var somarPreco = 0

    init {
        if (admin.senhaValidada) {
            println("Bem-vindo ao hotel $nome, ${admin.nome}! É um imenso prazer tê-lo por aqui!")
        } else {
            println("admin não logado. Redirecionando para o login..")
            admin.loginAdmin()
        }
    }

    fun agendarDiaria() {
        var nomeHospede: String = ""
        println("qual valor padrao da diaria?")
        val preco = lerEntradaNumerica()
        while(true){
        println("Quantos dias os hospedes desejam ficar hospedado?")
        user.qtdeDias = lerEntradaNumerica()
        if (user.qtdeDias <= 0 || user.qtdeDias >= 30) {
            println("Número de dias inválido. O número de dias deve ser entre 1 e 29.")
        }else{
            break
        }
        }

        while(nomeHospede!="pare"){
            println("qual o nome do hospede")
            nomeHospede = readln()
            if(nomeHospede=="pare"){
                break
            }
            println("qual a idade do hospede")
            val idade = lerEntradaNumerica()
            this.hospedes.add(nomeHospede)
            if(idade <7){
                println("tem entrada gratuitada")
                this.gratuitates++
                this.somarPreco+=0
            }else if(idade > 60){
                this.meias++
                println("paga meia")
                this.somarPreco+=(preco/2)
            }else{
                this.completas++
                println("paga entrada complete")
                this.somarPreco+=preco
            }

        }

        escolherQuarto()
        println("O valor total da estadia é R$ ${this.somarPreco * user.qtdeDias} sendo ${this.gratuitates} entradas gratuitas ${this.meias} meia entrada ${this.completas} entradas completas . Confirmar reserva? (S/N)")

        val confirmacao = readln().lowercase()
        if (confirmacao == "s") {
            println("Parabéns, o quarto de ${hospedes.joinToString()}, foi agendado com sucesso. O número do quarto é ${this.user.quartoAgendado}")
            usuarios.add(this.hospedes)
            quartosUsuarios.add(user.quartoAgendado)
        } else {
            println("Reserva cancelada.")
        }
    }

    private fun escolherQuarto() {
        println("Escolha um dos quartos disponíveis:")
        for (e in quartosDisponiveis) {
            print("$e, ")
        }
        println()
        val numeroQuarto = lerEntradaNumerica()
        if (quartosDisponiveis.contains(numeroQuarto)) {
            quartosDisponiveis.remove(numeroQuarto)
            this.user.quartoAgendado = numeroQuarto
            println("Quarto $numeroQuarto reservado para ${hospedes.joinToString()}!")
        } else {
            println("Quarto indisponível. Por favor, escolha outro.")
            escolherQuarto()
        }
    }

    fun mostrarHospedes(){
        if(usuarios.size == 0){
            println("nao há hospedes")
        }
        else{
            println("HOSPEDE${" ".padEnd(20)} | QUARTO") // Cabeçalho alinhado
            for ((i, e) in usuarios.withIndex()) {
                val hospede = e.joinToString().padEnd(20) // Alinha o nome do hóspede em 20 caracteres
                val quarto = quartosUsuarios[i].toString().padEnd(10) // Alinha o número do quarto em 10 caracteres
                println("$hospede | $quarto")
            }
        }

    }

    fun pesquisarHospedes(){
        var usuarioEncontrado = false
        println("qual nome do hospede")
        val nomePesquisado = readln()
        for ((i, e) in usuarios.withIndex()) {
           if(e.contains(nomePesquisado)){
               println("usuario $nomePesquisado esta no quarto ${quartosUsuarios[i]}")
               usuarioEncontrado = true
           }
        }
        if(!usuarioEncontrado){
            println("usuario nao encontrado")
        }
    }
}
