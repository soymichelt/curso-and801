package com.soymichel.runtimeform

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class RuntimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_runtime)

        //Create params for views---------------
        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        //Create a layout---------------
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        //----Create a TextView------
        val textView = TextView(this)

        textView.text = "This TextView is dynamically created"
        textView.layoutParams = params

        //--Create A EditText------------------
        val editText = EditText(this)
        editText.layoutParams = params

        //----Create a CheckBox-------------
        val checkBox = CheckBox(this)
        checkBox.layoutParams = params

        //--- Create a RadioGroup---------------
        val radioGroup = RadioGroup(this)
        radioGroup.layoutParams = params

        //--------Create a RadioButton----------
        val radioButton = RadioButton(this)
        radioButton.layoutParams = params

        //-----Create a Button--------
        val button = Button(this)
        button.setText("This Button is dynamically created")
        button.setLayoutParams(params)

        //---Add all elements to the layout
        linearLayout.addView(textView)
        linearLayout.addView(checkBox)
        linearLayout.addView(editText)
        linearLayout.addView(radioGroup)
        linearLayout.addView(radioButton)

        linearLayout.addView(button)

        //---Create a layout param for the layout-----------------
        val layoutParams =
            LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        this.addContentView(linearLayout, layoutParams)

    }

}