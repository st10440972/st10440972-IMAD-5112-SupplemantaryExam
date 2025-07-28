package vcmsa.ci.st10440972imad5112supp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var categoryTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var exitButton: Button

    private var currentIndex = 0

    private var titles = arrayListOf<String>()
    private var categories = arrayListOf<String>()
    private var rating = arrayListOf<Int>()
    private var ingredients = arrayListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        titleTextView = findViewById(R.id.titleTextView)
        categoryTextView = findViewById(R.id.catergoriesTextView)
        ratingTextView = findViewById(R.id.rateTextView)
        ingredientsTextView = findViewById(R.id.ingredientsTextView)
        nextButton = findViewById(R.id.nextButton)
        exitButton = findViewById(R.id.exitButton)

        titles = intent.getStringArrayListExtra("Titles") ?: arrayListOf()
        categories = intent.getStringArrayListExtra("Categories") ?: arrayListOf()
        rating = intent.getIntegerArrayListExtra("Ratings") ?: arrayListOf()
        ingredients = intent.getStringArrayListExtra("Ingredients") ?: arrayListOf()

        showRecipe()
        //setting up the list stored in the arrays
        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < titles.size) {
                showRecipe()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        //exiting application
        exitButton.setOnClickListener {
            finishAffinity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }

    @SuppressLint("SetTextI18n")
    private fun showRecipe() {
        titleTextView.text = "Title: ${titles[currentIndex]}"
        categoryTextView.text = "Category: ${categories[currentIndex]}"
        ratingTextView.text = "Rating: ${rating[currentIndex]}"
        ingredientsTextView.text = "Ingredients: ${ingredients[currentIndex]}"
    }


}