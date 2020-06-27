package com.example.task3.controllers.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BranchView(
        val id: Int,
        val title: String,
        val lat: Double,
        val lon: Double,
        val address: String,
        var distance: Int? = null,
        var dayOfWeek: Int? = null,
        var hourOfDay: Int? = null,
        var predicting: Int? = null
)