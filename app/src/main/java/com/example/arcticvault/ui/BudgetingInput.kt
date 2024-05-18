package com.example.arcticvault.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.model.BudgetingInputModel
import com.example.arcticvault.R
import kotlinx.coroutines.launch

object BudgetingInputDestination {
    val route = "BudgetingInput"
    val budgetIdArg = "budgetId"
    val routeWithArgs = "$route/{$budgetIdArg}"
}

@Composable
fun BudgetingInput(
    onPreviousButton: () -> Unit,
    onCancelButton: () -> Unit,
    budgetingInputViewModel: BudgetingInputViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val budgetingInputUiState by budgetingInputViewModel.uiState.collectAsState()
    val budgeting: BudgetingInputModel = budgetingInputUiState.budgeting
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            errorMessage = null
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(330.dp)
                .fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 40.dp)
        ) {
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
    //Text Field To Input Yearly Budget
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Text(
                text = "Yearly Budgeting:",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextField(
                value = budgetingInputViewModel.formatAmount(budgeting.yearlyBudgeting),
                onValueChange = {
                    budgetingInputViewModel.updateUiState(
                        budgeting.copy(
                            yearlyBudgeting = budgetingInputViewModel.updateAmount(it)
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green,
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

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(onClick = { onCancelButton() }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                if (budgetingInputViewModel.validateInput(budgetingInputUiState)) {
                    coroutineScope.launch {
                        budgetingInputViewModel.saveBudgeting(budgeting)
                    }
                    onCancelButton()
                } else {
                    errorMessage = "Please fill in all fields!"
                }
            }) {
                Text(text = "Save")
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(320.dp))
        Column {
            Text(
                text = "Previous Budgeting Before:",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            for (i in 0..2) {
                Row {
                    Text(
                        text = "${i.plus(1)}:",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${budgetingInputViewModel.budgetingList.getOrNull(i)?.yearlyBudgeting ?: ""}",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
