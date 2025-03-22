package org.example

val usuarios: MutableList<List<String>> = mutableListOf()
val quartosDisponiveis = (1..20).toMutableList()
val quartosUsuarios = mutableListOf<Int>()
val auditoriosReservados = mutableListOf<String>()
val datasDeEventos = mutableListOf<List<Any>>()
val empresas = mutableListOf<String?>()
val valoresPorEmpresa = mutableListOf<Double?>()
val valorLanche = mutableListOf<Double?>()


fun main() {
    val admin = Admin()
    iniciarMenu(admin)
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
            |6- cadastrar Evento
            |7- cadastrar Servicos
            |8- verificar Empresas e valores
            |9- calcularGasolina
            """.trimMargin()
        )
        val escolhaMenu = Utils.lerEntradaNumerica()
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
            6->{
                val evento = Evento(admin)
                evento.cadastrarEvento()
            }
            7->{
                val hotel = Hotel(admin)
                hotel.cadastrarServico()
            }
            8->{
                val hotel = Hotel(admin)
                hotel.mostrarEmpresasEValores()
            }
            9-> {
                val veiculos = Veiculo()
                veiculos.calcularValorPosto()
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}










