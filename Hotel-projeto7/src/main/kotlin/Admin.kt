package org.example

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
            val senha = Utils.lerEntradaNumerica()
            if (senha == 2678) {
                this.senhaValidada = true
                break
            } else {
                println("Senha inválida. Tente novamente.")
            }
        }
    }
}