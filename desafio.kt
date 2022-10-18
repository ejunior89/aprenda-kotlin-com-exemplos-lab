// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val email: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    // Função para matricular um usuário na formação
    fun matricular(usuario: Usuario) {
        if (usuario !in inscritos) {
            inscritos.add(usuario)
            println("${usuario.nome} matriculado na formação ${this.nome}.")
        } else {
            println("${usuario.nome} já está matriculado na formação ${this.nome}.")
        }
    }

    // Função para calcular a média de duração dos conteúdos
    fun calcularMediaDuracaoConteudos(): Double {
        return conteudos.sumByDouble { it.duracao.toDouble() } / conteudos.size
    }

    // Função para calcular o nível de dificuldade com base na média de duração dos conteúdos
    fun calcularNivelDeDificuldade(): Nivel {
        val mediaDuracao = calcularMediaDuracaoConteudos()
        return when {
            mediaDuracao >= 180 -> Nivel.DIFICIL
            mediaDuracao >= 120 -> Nivel.INTERMEDIARIO
            else -> Nivel.BASICO
        }
    }

    // Função para verificar se um conteúdo foi excluído com base no nome fornecido
    fun verificarConteudoExcluido(nomeConteudo: String): Boolean {
        return conteudos.none { it.nome == nomeConteudo }
    }
}

fun main() {
    val usuario1 = Usuario("João", "joao@email.com")
    val usuario2 = Usuario("Maria", "maria@email.com")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 130)
    val conteudo2 = ConteudoEducacional("Conceitos básicos de programação", 90)
    val conteudo3 = ConteudoEducacional("Desenvolvimento Avançado com Kotlin", 180)

    val formacao = Formacao("Desenvolvimento Backend com Kotlin", listOf(conteudo1, conteudo2, conteudo3))

    // Exibir a média de duração dos conteúdos e o nível de dificuldade da formação
    println("Média de duração dos conteúdos: ${formacao.calcularMediaDuracaoConteudos()} minutos")
    println("Nível de dificuldade da formação: ${formacao.calcularNivelDeDificuldade()}")

    // Verificar se um conteúdo foi excluído da formação
    println("Conteúdo 'Conceitos básicos de programação' está excluído? ${formacao.verificarConteudoExcluido("Conceitos básicos de programação")}")
    println("Conteúdo 'Algoritmos e Estruturas de Dados' está excluído? ${formacao.verificarConteudoExcluido("Algoritmos e Estruturas de Dados")}")

    // Matricular os usuários e exibir informações
    formacao.matricular(usuario1)
    formacao.matricular(usuario2)
    formacao.matricular(usuario1) // Teste para verificar duplicidade

    println("Total de inscritos na formação: ${formacao.inscritos.size}")
}


