package org.example

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
        val preco = Utils.lerEntradaNumerica()
        while(true){
            println("Quantos dias os hospedes desejam ficar hospedado?")
            user.qtdeDias = Utils.lerEntradaNumerica()
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
            val idade = Utils.lerEntradaNumerica()
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
        val numeroQuarto = Utils.lerEntradaNumerica()
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

     fun cadastrarServico() {
        var resposta: String
        do {
            println("qual nome da empresa ")
            val nomeEmpresa = readLine() ?: ""
            println("qual valor do serviço ")
            val valor = readLine()?.toDoubleOrNull() ?: 0.0
            println("qual a quantidade de aparelhos em manutenção ")
            val quantidadeDeAparelhos = readLine()?.toIntOrNull() ?: 0
            println("qual a quantidade minima de aparelhos para promoção ")
            val quantidadeMinimaDeAparelhos = readLine()?.toIntOrNull() ?: 0

            var valorDesconto = 0.0
            if (quantidadeMinimaDeAparelhos <= quantidadeDeAparelhos) {
                println("quantos porcento de desconto? ")
                valorDesconto = readLine()?.toDoubleOrNull() ?: 0.0
                valorDesconto /= 100
            }

            var valorFinal = valor * quantidadeDeAparelhos
            valorFinal -= valorFinal * valorDesconto

            empresas.add(nomeEmpresa)
            valoresPorEmpresa.add(valorFinal)

            println("serviço feito com a $nomeEmpresa de $quantidadeDeAparelhos aparelhos por $valorFinal \n")
            println("Deseja calcular outros servicos?")
            resposta = readLine() ?: ""
        } while (resposta.equals("s", ignoreCase = true))
    }

     fun mostrarEmpresasEValores() {
        if (empresas.size > 0) {
            println("empresa | Valor Serviço")
            for ((i,e) in empresas.withIndex()) {
                println("${empresas[i]} | ${valoresPorEmpresa[i]} ")
            }
        } else {
            println("nenhuma empresa cadastrada")
        }
    }
}