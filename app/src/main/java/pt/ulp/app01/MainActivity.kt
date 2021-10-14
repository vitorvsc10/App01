package pt.ulp.app01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var valorEditText: EditText
          //  =  findViewById(R.id.valor_text_number)

    var numNotas: Int = 0
    // val   constante
    // var   variável
    var somaNotas: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // instanciar a parte gráfica com os elementos definidos
        setContentView(R.layout.activity_main)

        // criar um objeto que representa a caixa de texto...
        valorEditText =  findViewById(R.id.valor_text_number)

        // configurações adicionais.
        // 1. alterar o conteúdo da etiqueta de saudação
        //    para "Olá nome do aluno"
        //    pseudocódigo
        //          R.id.saudacao_text_view" .text <- "Olá .."
        //

        // 1ª opção
        /* findViewById<TextView>(R.id.saudacao_text_view)
            .text = "Olá ULP!"
        */

        // 2ª opção (recorrendo a uma variável do tipo TextView)
        var saudacaoTextView:TextView  =  findViewById(R.id.saudacao_text_view)
        saudacaoTextView.text = "Olá Eng Informática!"
        saudacaoTextView.text = getString(R.string.texto_saudacao_eng_inf)
         //  getString(R.string.texto)
         //     consulta strings.xml e devolve o valor do item designado por 'texto'
         //     semelhante ao que se usa no editor propriedades: @string/texto

        // 3ª opção (recorrendo a uma variável "sem tipo")
        /*
        var obj =  findViewById<TextView>(R.id.saudacao_text_view)
        obj.text = "Olá Prog disp Móveis!"
        */

        // 2. definir comportamento ao clicar no botão
        findViewById<Button>(R.id.calcular_button)
            .setOnClickListener{ calcularResultado() }
     }

    fun calcularMedia(n: Int, s: Int) : Double
    {
        return (s*1.0)/n
    }

    fun calcularResultado() {
        // alterar resultado
        var resultadoTextView:TextView
                =  findViewById(R.id.resultado_text_view)
        resultadoTextView.text = getString(R.string.texto_a_calcular)
        // consultar valor na caixa de texto
        // var valorEditText: EditText
        //        =  findViewById(R.id.valor_text_number)
        resultadoTextView.text = valorEditText.getText().toString()


        // converter valor da caixa de texto para inteiro
        val valorNota:Int = valorCaixaTexto()


        // calcular o resultado
        numNotas ++
        somaNotas += valorNota
        val media: Double = calcularMedia(numNotas, somaNotas)

        // Mecanimos para mostrar mensagens
        // (neste caso mostrar os dados calculados)
        // Tost - mesnagem breve para o utilizador final (não usar como debugging)
        Toast.makeText(this,                   // o contexto (view) onde irá aparecer
            getString(R.string.texto_mensagem_nota), //  a mensagem de texto a mostrar
            Toast.LENGTH_LONG                        //  duração da mensagem
            ).show()                                 // método que trata de mostrar o Toast

        // Logging -  mensagem destinadas a quem desenvolve e mantém a aplicação
        //            irá aparecer na janela LogCat do Android Studio
        //    Log.d(   )    - mensagem de debugging
        Log.d("calculaResultado",   // etiqueta para identificar o contexto da mensagem, eg. função
            "n: $numNotas; soma:$somaNotas; media: $media " // texto a mostrar...
            )

        // --------------------------
        // atualizar a etiqueta N notas
        //  1 nota
        // resultadoTextView.text = numNotas + "nota!"
        // resultadoTextView.text = "$numNotas nota!"
        /*
        resultadoTextView.text = getString(
                      R.string.texto_num_notas,
                      numNotas )
        */
        resultadoTextView.text = resources.getQuantityString(
            R.plurals.texto_num_notas,
            numNotas, // quantidade para decidir
            numNotas  // valor para substituir %1$d
             )

        // atualizar a etiqueta da média
        val mediaTextView:TextView
                =  findViewById(R.id.media_text_view)
        mediaTextView.text = getString(
            R.string.texto_resultado_media, // media: %1$f
            media                  // valor a colocar em %1$f
         )
        // Apagar o conteúdo da caixa de texto
        valorEditText.text.clear()
    }

    fun valorCaixaTexto(): Int {
        // var valorEditText: EditText
        //        =  findViewById(R.id.valor_text_number)
       return  valorEditText.text.toString().toInt()
    }

}

