package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.Model.EditGoalsInputModel
import com.example.arcticvault.R
import com.example.arcticvault.ui.theme.theme.AppViewModelProvider
import com.example.arcticvault.ui.theme.theme.EditGoalsInputViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object EditGoalsInputDestination {
    val route = "EditGoalsInput"
    val goalIdArg = "goalId"
    val routeWithArgs = "${EditGoalsInputDestination.route}/{$goalIdArg}"
}

@Composable
fun EditGoalsInput(
    editGoalsInputViewModel: EditGoalsInputViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onPreviousButton:() -> Unit,
    onCancelButton:() -> Unit,
){
    val editGoalsInputUiState by editGoalsInputViewModel.uiState.collectAsState()
    val editGoals: EditGoalsInputModel = editGoalsInputUiState.editGoalsInput
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
                text = "Edit Goals",
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
    //Title
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Title:",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            TextField(
                value = editGoals.title,
                onValueChange = { editGoalsInputViewModel.updateUiState(editGoals.copy(title = it)) },
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
    }
    //Amount
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Spacer(modifier = Modifier.height(350.dp)) // Adjusted spacer height
            Text(
                modifier = Modifier.padding(start = 30.dp), // Adjusted padding to add space between text and TextField
                text = "Amount:",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            TextField(
                value = editGoalsInputViewModel.formatAmount(editGoals.amount),
                onValueChange = { editGoalsInputViewModel.updateUiState(editGoals.copy(amount = editGoalsInputViewModel.updateAmount(it))) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                ),
                singleLine = true,
                colors = TextFieldDefaults
                    .colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Transparent
                    ),
            )
        }
    }
    //Milestones
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(
            text = "Milestones:",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            TextButton(onClick = { if(editGoals.milestones > 1){
                editGoalsInputViewModel.updateUiState(editGoals.copy(milestones = editGoals.milestones.minus(1)))
            }
            }) {
                Image(
                    painter = painterResource(R.drawable.minus),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(50.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.number),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(50.dp),
                )
                Text(
                    text = editGoals.milestones.toString(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            TextButton(onClick = { editGoalsInputViewModel.updateUiState(editGoals.copy(milestones = editGoals.milestones.plus(1))) }) {
                Image(
                    painter = painterResource(R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(45.dp)
                )
            }
        }
    }
    //Date
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(300.dp))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Date:",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }
        Image(
            painter = painterResource(R.drawable.calendaricon),
            contentDescription = stringResource(R.string.percentage_card_desc),
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    editGoalsInputViewModel.showDatePicker = true
                }
        )
        if (editGoalsInputViewModel.showDatePicker) {
            DateRangePickerDialog(
                editGoals = editGoals,
                editGoalsInputViewModel = editGoalsInputViewModel,
                onSaveClicked = { editGoalsInputViewModel.showDatePicker = false },
                onBackClicked = { editGoalsInputViewModel.showDatePicker = false }
            )
        }
        Row (

        ) {
            Column {
                Text(
                    text = "Start Date" + "\n" +editGoalsInputUiState.editGoalsInput.startDate ,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
            Column {
                Text(
                    text = "-",
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
            Column {
                Text(
                    text ="End Date" + "\n" + editGoalsInputUiState.editGoalsInput.endDate,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
        }
    }
    editGoalsInputViewModel.updateUiState(editGoals.copy(amountGetDivided = editGoals.amount / editGoals.milestones))
    //Save Button And Cancel Button
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            Button(onClick = { onCancelButton() }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                coroutineScope.launch {
                    editGoalsInputViewModel.saveEditGoals(editGoals)
                    onCancelButton()
            } }) {
                Text(text = "Save")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    editGoals: EditGoalsInputModel,
    editGoalsInputViewModel: EditGoalsInputViewModel,
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit
) {
    val snackState = remember { SnackbarHostState() }
    val datePickerState = rememberDateRangePickerState()
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))

    val selectedStartDate = remember(datePickerState.selectedStartDateMillis) {
        datePickerState.selectedStartDateMillis?.let { dateInMillis ->
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(dateInMillis))
        }
    }

    val selectedEndDate = remember(datePickerState.selectedEndDateMillis) {
        datePickerState.selectedEndDateMillis?.let { dateInMillis ->
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(dateInMillis))
        }
    }
    // Calculate number of days between start and end dates
    val startDate = selectedStartDate?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) }
    val endDate = selectedEndDate?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy")) }
    if (startDate != null && endDate != null) {
        val daysBetween = calculateDaysBetween(startDate, endDate)
        val dateGetDivided = daysBetween/editGoals.milestones
        // Calculate the new date by adding dateGetDivided days to the startDate
        val newDate = startDate.plusDays(dateGetDivided)
        val formattedDate = newDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    editGoalsInputViewModel.updateUiState(editGoals.copy(dateOfDivided = formattedDate))
    }
    DatePickerDialog(
        onDismissRequest = { onBackClicked() },
        confirmButton = {
            Button(
                onClick = {
                    onSaveClicked()
                    editGoalsInputViewModel.updateUiState(editGoals.copy(startDate = selectedStartDate ?: "Start Date" , endDate = selectedEndDate ?: "End Date"))
                },
            ) {
                Text(
                    text = "Select",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(onClick = {
                onBackClicked()
            }) {
                Text(
                    text = "Cancel",
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    ) {
        DateRangePicker(
            state = datePickerState,
        )
    }
}

fun calculateDaysBetween(startDate: LocalDate, endDate: LocalDate): Long {
    return endDate.toEpochDay() - startDate.toEpochDay()
}

