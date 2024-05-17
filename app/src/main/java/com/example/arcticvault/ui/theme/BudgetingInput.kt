package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.Model.BudgetingInputModel
import com.example.arcticvault.R
import com.example.arcticvault.ui.theme.theme.AppViewModelProvider
import com.example.arcticvault.ui.theme.theme.BudgetingInputViewModel

object budgetingInputDestination {
    val route = "BudgetingInput"
}
@Composable
fun BudgetingInput(
    onPreviousButton:() ->Unit,
    budgetingInputViewModel: BudgetingInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val budgetingInputUiState by budgetingInputViewModel.uiState.collectAsState()
    val budgeting:BudgetingInputModel = budgetingInputUiState.budgeting
    val coroutineScope = rememberCoroutineScope()

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.topbannercropped),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(330.dp)
                .fillMaxSize()
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 40.dp)
        ){
            TextButton(onClick = { onPreviousButton() }) {
                Image(
                    painter = painterResource(R.drawable.backbuttoncropped),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                )
            }
            Text(
                text = "Budgeting",
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f),
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.backbuttoncropped),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
    Column {
            TextField(
        value = budgetingInputViewModel.formatAmount(budgeting.yearlyBudgeting),
        onValueChange = { budgetingInputViewModel.updateUiState(budgeting.copy(yearlyBudgeting = budgetingInputViewModel.updateAmount(it))) },
        placeholder = {
            Text(
                text = "Subscription",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,

                )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 25.sp,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        ),
        colors = TextFieldDefaults
            .colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
        )
    }
    budgetingInputViewModel.updateUiState(budgeting.copy(monthlyBudgeting = monthly(budgeting.yearlyBudgeting)))
    budgetingInputViewModel.updateUiState(budgeting.copy(percentageYearlyBudgeting = percentageOfLinear(budgeting.yearlyBudgeting,budgeting.yearlyExpense)))
}

fun monthly(monthly:Double):Double{
    var monthlyExpense:Double = 0.0
    monthlyExpense = (monthly/12)
    return monthlyExpense
}

fun percentageOfLinear(expense: Double, budgeting: Double):Double{
    var percentage:Double = 0.0
    percentage = expense/budgeting * 100
    return percentage
}