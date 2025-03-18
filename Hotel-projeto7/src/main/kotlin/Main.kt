package org.example
val usuarios: MutableList<List<String>> = mutableListOf()
val quartosDisponiveis = (1..20).toMutableList()
val quartosUsuarios = mutableListOf<Int>()
val auditoriosReservados = mutableListOf<String>()
val datasDeEventos = mutableListOf<List<Any>>()

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
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}










