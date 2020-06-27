package com.example.task3.controllers

import com.example.task3.controllers.model.BranchView
import com.example.task3.controllers.model.ErrorView
import com.example.task3.services.BranchException
import com.example.task3.services.BranchService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class BranchesController(val branchService: BranchService) {

    @GetMapping("/branches/{id}/predict")
    fun predict(@PathVariable("id") id: Int,
                @RequestParam dayOfWeek: Int,
                @RequestParam hourOfDay: Int): BranchView {
        return branchService.predict(id, dayOfWeek, hourOfDay)
    }

    @GetMapping("/branches")
    fun nearBranch(@RequestParam("lat") lat: Double, @RequestParam("lon") lon: Double): BranchView {
        return branchService.getNearBranch(lat, lon)
    }

    @GetMapping("/branches/{id}")
    fun getBranch(@PathVariable("id") id: Int): BranchView {
        return branchService.getBranch(id)
    }

    @ExceptionHandler(BranchException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(e: BranchException): ErrorView {
        return ErrorView(e.message ?: "")
    }
}