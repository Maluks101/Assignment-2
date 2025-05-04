package vcmsa.ci.assignment2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView


class Pg4 : AppCompatActivity() {

    private val questions = arrayOf(
        "Arsenal have won the Champions League",
        "Real Madrid have won 14 Champions Leagues",
        "Petr Cech once played left wing",
        "Manuel Neuer finished top 3 for the Ballon d'or in 2014",
        "No keeper has won the Ballon d'or")
    private val answer = arrayOf(false,true,true,true,false)
    private var currentQindex = 0
    private var score = 0
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var txtResults: TextView
    private lateinit var txtQuestion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg2)

        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        txtResults = findViewById(R.id.txtResults)
        txtQuestion = findViewById(R.id.txtQuestion)
        btnNext = findViewById(R.id.btnNext)

        showQuestion()

        btnTrue.setOnClickListener{
            verifyAnswer(true)
        }
        btnFalse.setOnClickListener{
            verifyAnswer(false)
        }
        btnNext.setOnClickListener{
            currentQindex++
            if(currentQindex<questions.size){
                showQuestion()
            }else{
                val intent = Intent(this, Pg3::class.java)
                intent.putExtra("Score",score)
                startActivity(intent)
                finish()
            }
        }








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun verifyAnswer(personsAnswer:Boolean) {
        val rightAnswer = answer[currentQindex]
        if(personsAnswer==rightAnswer){
            txtResults.text = "You are Right"
            score++
        }else{
            txtResults.text = "You are Wrong"
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true



    }

    private fun showQuestion() {
        for (x in currentQindex until questions.size) {
            txtResults.text = questions[x]
            txtQuestion.text = ""
        }
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.isEnabled = false

    }
}