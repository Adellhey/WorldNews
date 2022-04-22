package com.example.worldnews.presentation

sealed class ProcessUiEvent {

    data class InitTypeOfNews(val type: TypeOfNews): ProcessUiEvent()
}
