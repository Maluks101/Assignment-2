package vcmsa.ci.assignment2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pg3 : AppCompatActivity() {
    private val questions = arrayOf(
        "Arsenal have won the Champions League",
        "Real Madrid have won 14 Champions Leagues",
        "Petr Cech once played left wing",
        "Manuel Neuer finished top 3 for the Ballon d'or in 2014",
        "No keeper has won the Ballon d'or"
    )
    private val answer = arrayOf(false, true, true, true, false)
    private lateinit var btnExit: Button
    private lateinit var btnReview: Button
    private lateinit var txtScores: TextView
    private lateinit var txtFeed: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg3)
        val correctScore = intent.getIntExtra("score", 0) //Carries over the scores from Pg2

        btnExit = findViewById(R.id.btnExit)
        btnReview = findViewById(R.id.btnReview)
        txtScores = findViewById(R.id.txtScores)
        txtFeed = findViewById(R.id.txtFeed)



        val responseMessage = if (correctScore >= 3) //Message user receives after completing the quiz
             "Good job!"
        else
             "Keep trying!"

        txtScores.text = "Your Score: $correctScore out of 5\n $responseMessage" // Score user achieved


        
        btnReview.setOnClickListener { // Review button for the questions
            showReview()
        }
        btnExit.setOnClickListener{ // Closes the app
            finishAffinity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showReview() {
        var textReview = ""
        for (x in questions.indices) {
            textReview += "${ x + 1 }.${questions[x]} - Correct Answer: ${if (answer[x]) "True" else "False"}\n\n" // Review of the correct answers

            }
            txtFeed.text = textReview // Screen that shows the correct answers
        }
    }
