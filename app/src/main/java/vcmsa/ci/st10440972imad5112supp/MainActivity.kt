package vcmsa.ci.st10440972imad5112supp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Declaring arrays to store data
    private val recipeTitles = arrayListOf<String>()
    private val recipeCategories = arrayListOf<String>()
    private val recipeRatings = arrayListOf<Int>()
    private val recipeIngredients = arrayListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Declaring all variables
        val titleEdit = findViewById<EditText>(R.id.recipeTextView)
        val categoryEdit = findViewById<EditText>(R.id.categoryTextView)
        val ratingEdit = findViewById<EditText>(R.id.ratingTextView)
        val ingredientsEdit = findViewById<EditText>(R.id.ingredientsTextView)
        val addButton = findViewById<Button>(R.id.addButton)
        val reviewButton  = findViewById<Button>(R.id.reviewButton)
        val exitingButton = findViewById<Button>(R.id.exitingButton)

        //setting up button click for adding and storing input data
        addButton.setOnClickListener {
            val title = titleEdit.text.toString()
            val category = categoryEdit.text.toString()
            val rating = ratingEdit.text.toString().toIntOrNull() ?: 0
            val ingredients = ingredientsEdit.text.toString()

            //save to arrays
            recipeTitles.add(title)
            recipeCategories.add(category)
            recipeRatings.add(rating)
            recipeIngredients.add(ingredients)

            Toast.makeText(this, "Recipe Added!!", Toast.LENGTH_SHORT).show()

            //clearing inputs
            titleEdit.text.clear()
            categoryEdit.text.clear()
            ratingEdit.text.clear()
            ingredientsEdit.text.clear()
        }

        //setting up screen navigation (to allow users to see their input on the next page)
        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putStringArrayListExtra("Title", recipeTitles)
            intent.putStringArrayListExtra("Categories", recipeCategories)
            intent.putIntegerArrayListExtra("Ratings", ArrayList(recipeRatings))
            intent.putStringArrayListExtra("Ingredients", recipeIngredients)
            startActivity(intent)
        }

        exitingButton.setOnClickListener {
            finishAffinity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}