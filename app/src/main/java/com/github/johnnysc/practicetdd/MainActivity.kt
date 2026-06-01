package com.github.johnnysc.practicetdd

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mediator by lazy { Mediator.Base() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val choiceOne = findViewById<ChoiceButton>(R.id.firstChoiceButton)
        val choiceTwo = findViewById<ChoiceButton>(R.id.secondChoiceButton)
        val choiceThree = findViewById<ChoiceButton>(R.id.thirdChoiceButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        choiceOne.init(mediator) { saveButton.isEnabled = true }
        choiceTwo.init(mediator) { saveButton.isEnabled = true }
        choiceThree.init(mediator) { saveButton.isEnabled = true }
    }
}