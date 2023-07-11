package com.curiousapps.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun `Provides Expected Question`(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }
    @Test
    fun `Provides Expected Question at current index`(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 4))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_americas, quizViewModel.currentQuestionText)
    }

    @Test
    fun `Array moves to next question bank`(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_ocean, quizViewModel.currentQuestionText)
    }
    @Test
    fun `Array wraps around question bank`(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }
    @Test
    fun `Array wraps around question bank from end`(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 0))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
        quizViewModel.moveToPrevious()
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
    }
}