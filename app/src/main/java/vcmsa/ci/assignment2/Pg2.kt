package vcmsa.ci.assignment2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.provider.SyncStateContract.Constants
import android.widget.Button
import android.widget.TextView


class Pg2 : AppCompatActivity() {

    private val questions = arrayOf(
        "Arsenal have won the Champions League",
        "Real Madrid have won 14 Champions Leagues",
        "Petr Cech once played left wing",
        "Manuel Neuer finished top 3 for the Ballon d'or in 2014",
        "No keeper has won the Ballon d'or"
    ) // Questions of the quiz
    private val answer = arrayOf(false, true, true, true, false) // Answers to the questions
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
           verifyAnswer(personsAnswer = true) //True button
       }
       btnFalse.setOnClickListener{
           verifyAnswer(personsAnswer = false) //False button
       }
       btnNext.setOnClickListener{
           currentQindex++ // Moves on to the next question
           if(currentQindex < questions.size){
               showQuestion()
           }else{
               val intent = Intent(this, Pg3::class.java) //Pushes information onto Pg3
               intent.putExtra("score", score)
               startActivity(intent)
               finish()
           }
       }

    }

    private fun verifyAnswer(personsAnswer: Boolean) {
        val rightAnswer = answer[currentQindex] //Confirms whether the user input is correct or not
        if(personsAnswer == rightAnswer){
            txtResults.text = "You are Right" // Responses based on the user input
            score++
        }else{
            txtResults.text = "You are Wrong"
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true

    }

    private fun showQuestion() {
        for (x in currentQindex until questions.size) { //Shows the questions on screen as cycles through them
            txtResults.text = questions[x]
            txtQuestion.text = ""
            break
        }
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.isEnabled = false

    }
}