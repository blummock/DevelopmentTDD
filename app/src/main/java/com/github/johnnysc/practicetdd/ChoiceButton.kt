package com.github.johnnysc.practicetdd

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class ChoiceButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr), Choice {

    private var mediator: Mediator? = null
    private var isChosen = false

    override fun init(mediator: Mediator, block: () -> Unit) {
        this.mediator = mediator
        setOnClickListener {
            this.mediator?.change(this, block)
        }
    }

    override fun isChosen() = isChosen

    override fun chose() {
        isEnabled = false
        isChosen = true
        updateBackground()
    }

    override fun rollback() {
        isEnabled = true
        isChosen = false
        updateBackground()
    }

    private fun updateBackground() {
        if (isChosen) {
            setBackgroundColor(Color.CYAN)
            setTextColor(Color.BLACK)
        } else {
            setBackgroundColor(Color.GRAY)
            setTextColor(Color.WHITE)
        }
    }
}
