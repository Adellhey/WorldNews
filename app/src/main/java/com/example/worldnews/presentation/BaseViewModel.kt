package com.example.worldnews.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<U>: AndroidViewModel(Application()) {
    abstract fun processUiEvent(event: U)
}