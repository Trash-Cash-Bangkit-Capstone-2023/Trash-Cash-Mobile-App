package com.example.trashcash.helper

import android.view.View
import android.widget.ProgressBar

class ProgressBarHandler(private val progressBar: ProgressBar) {
    fun show() {
        progressBar.visibility = View.VISIBLE
    }

    fun hide() {
        progressBar.visibility = View.GONE
    }
}