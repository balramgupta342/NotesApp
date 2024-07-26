package com.example.notesapp.features_notes.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

}