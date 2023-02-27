package com.example.validator_lib

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.validator_spinner.view.*

class ValidatorSpinner(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val spinner: Spinner

    init {
        inflate(context, R.layout.validator_spinner, this)

        spinner = findViewById(R.id.spinner)
    }


    fun setError() {
        spinner_img.setImageResource(R.drawable.ic_round_error_24)
    }

    fun setAdapter(adapter: SpinnerAdapter) {
        spinner.adapter = adapter
    }


    fun isItemSelected(): Boolean {
        if (spinner.selectedItemPosition == 0) {
            setError()
        } else {
            spinner_img.setImageResource(R.drawable.ic_round_check_circle_24)

        }
        return spinner.selectedItemPosition != 0
    }


    fun setImgGone() {
        spinner_img.setImageResource(0)
        spinner.setSelection(0)

    }

    class ListExampleAdapter(context: Context) : BaseAdapter() {
        private val sList: Array<String> =
            arrayOf(
                "< Please select one >",
                "USA",
                "Argentina",
                "UK"
            )
        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return sList.size
        }

        override fun getItem(position: Int): Any {
            return sList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.label_spinner, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.label.text = sList[position]
            if (position == 0) {
                vh.label.setTextColor(Color.RED)
            }
            return view
        }

        override fun isEnabled(position: Int): Boolean {
            if (position == 0) {

                return false
            }
            return super.isEnabled(position)
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row?.findViewById(R.id.label) as TextView
        }
    }
}