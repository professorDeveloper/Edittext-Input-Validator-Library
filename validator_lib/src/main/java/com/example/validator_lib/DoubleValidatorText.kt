package com.example.validator_lib

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout

@SuppressLint("CustomViewStyleable")
class DoubleValidatorText(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private var atributes: TypedArray
    private var firstEditText: EditText
    private var secondEditText: EditText
    private var firstPassword: String
    private var secondPassword: String
    private val isPassword: Boolean

    init {
        inflate(context, R.layout.double_edit_txt, this)
        atributes = context.obtainStyledAttributes(attrs, R.styleable.DoubleEditText)
        firstEditText = findViewById(R.id.password)
        secondEditText = findViewById(R.id.re_password)
        firstPassword = firstEditText.text.toString()
        secondPassword = secondEditText.text.toString()
        isPassword =
            atributes.getBoolean(R.styleable.DoubleEditText_passwordEnabled, true)
        setUp()
    }

    private fun setUp() {
        val maxLength = atributes.getInt(R.styleable.DoubleEditText_maxLength, 25)
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = InputFilter.LengthFilter(maxLength)
        firstEditText.filters = fArray
        secondEditText.filters = fArray
        if (!isPassword) {
            firstEditText.inputType = InputType.TYPE_CLASS_TEXT
            secondEditText.inputType = InputType.TYPE_CLASS_TEXT
            firstEditText.hint = "First Name"
            secondEditText.hint = "Last Name"
        } else {
            firstEditText.hint = "Enter password"
            secondEditText.hint = "Re-enter password"
        }

        secondEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString() == firstEditText.text.toString() && isPassword) {
                        secondEditText.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_round_check_circle_24,
                            0
                        )
                    } else if (p0.toString() != firstEditText.text.toString() && isPassword) {
                        secondEditText.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_round_error_24,
                            0
                        )
                    }

            }

            override fun afterTextChanged(s: Editable?) {

            }


        })
    }


    private fun getFirstText(): String {
        firstPassword = firstEditText.text.toString()
        return firstPassword
    }

    private fun getSecondText(): String {
        secondPassword = secondEditText.text.toString()
        return secondPassword
    }

    private fun isCompatible(): Boolean {
        return (getFirstText() == getSecondText())
    }

    private fun setErrorWithDrawable() {
        if (isPassword)
            secondEditText.error = "Re-enter password"
        secondEditText.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_round_error_24,
            0
        )
    }

    private fun setErrorWithDrawable(msg: String) {
        secondEditText.error = msg
        secondEditText.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_round_error_24,
            0
        )
    }

    fun clear() {
        firstEditText.text.clear()
        secondEditText.text.clear()
        firstEditText.setCompoundDrawables(null, null, null, null)
        secondEditText.setCompoundDrawables(null, null, null, null)
    }

    fun isNotEmpty(): Boolean {
        return (firstEditText.text.isNotEmpty() && secondEditText.text.isNotEmpty())
    }

    fun checkTextAndSetError() {
        if (isPassword && !isCompatible() || firstEditText.text.isEmpty()) {
            setErrorWithDrawable()
        } else if ((firstEditText.text.isEmpty() || secondEditText.text.isEmpty()))
            setErrorWithDrawable("Please re-enter")
        else {
            secondEditText.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_round_check_circle_24,
                0
            )
        }
    }
}