package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.EditGoalsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class EditGoalsViewModel(
    savedStateHandle: SavedStateHandle,
    categoryRepository: CategoryRepository,
    private val editGoalsRepository: EditGoalsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditGoalsUiState())
    val numberChanges: Int? = savedStateHandle[EditGoalsDestination.goalIdArg]


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var selectedCategoryId by mutableIntStateOf(0)

    val editGoalsUiState: StateFlow<EditGoalsUiState> =
        editGoalsRepository.getAllEditGoalsStream().map { EditGoalsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EditGoalsUiState()
            )

    init {
        viewModelScope.launch {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    var categoryList: List<Category> = listOf()

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }
}