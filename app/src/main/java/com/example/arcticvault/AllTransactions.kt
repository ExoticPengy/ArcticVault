package com.example.arcticvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.ui.AllTransactionsViewModel
import com.example.arcticvault.ui.AppViewModelProvider
import com.example.arcticvault.ui.theme.montserratFontFamily
import java.text.SimpleDateFormat
import java.util.Locale

object AllTransactionsDestination {
    val route = "All"
}


@Composable
fun AllTransactions(
    onTransactionClick: (Int) -> Unit,
    onBackButtonClick: () -> Unit,
    allTransactionsViewModel: AllTransactionsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val allTransactionsUiState by allTransactionsViewModel.allTransactionsUiState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        var isIncome by remember { mutableStateOf(true) }
        var searchFilter by remember { mutableStateOf("Search") }
        val titleText = when(isIncome) {
            true -> "Income"
            false -> "Expense"
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //Box for top banner
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.topbanner),
                    contentDescription = stringResource(R.string.blue_topbanner_desc),
                    modifier = Modifier
                        .height(330.dp)
                        .fillMaxWidth()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .width(320.dp)
                            .padding(top = 40.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.backbutton),
                            contentDescription = stringResource(R.string.back_button_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    onBackButtonClick()
                                }
                        )
                        Text(
                            text = titleText,
                            fontFamily = montserratFontFamily,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(R.drawable.backbutton),
                            contentDescription = stringResource(R.string.back_button_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.percentagecard),
                            contentDescription = stringResource(R.string.percentage_card_desc),
                            modifier = Modifier
                                .height(50.dp)
                                .width(310.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    isIncome = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Text(
                                    text = "Income",
                                    fontFamily = montserratFontFamily,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(
                                color = Color.White,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(20.dp)
                            )
                            Button(
                                onClick = {
                                          isIncome = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Text(
                                    text = "Expense",
                                    fontFamily = montserratFontFamily,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    TextField(
                        value = searchFilter,
                        onValueChange = { searchFilter = it },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.magnifyingglassicon),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            ) },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                                ),
                        modifier = Modifier
                            .height(50.dp)
                            .border(
                                width = 3.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(40)
                            )
                    )
                    Spacer(Modifier.height(25.dp))
                    Row {
                        DatePicker(50)
                        Spacer(Modifier.width(30.dp))
                        Image(
                            painter = painterResource(R.drawable.filtericon),
                            contentDescription = stringResource(R.string.percentage_card_desc),
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                }
                        )
                    }
                }
            }
            Spacer(Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(R.drawable.percentagecard),
                    contentDescription = stringResource(R.string.percentage_card_desc),
                    modifier = Modifier
                        .height(50.dp)
                        .width(310.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Total",
                        color = Color.White,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )

                    Text(
                        text = "RMXXX.XX",
                        color = Color.White,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val transactionList: List<Transaction> = allTransactionsUiState.transactionList
                items(transactionList) { transaction ->
                    TransactionTexts(onTransactionClick = { onTransactionClick(it) }, transaction = transaction, viewModel = allTransactionsViewModel)
                }
            }
        }
    }
}

@Composable
fun TransactionTexts(
    onTransactionClick: (Int) -> Unit, transaction: Transaction,
    viewModel: AllTransactionsViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .width(310.dp)
            .clickable {
                onTransactionClick(transaction.id)
            }
    ) {
        Image(
            painter = painterResource(transaction.icon),
            contentDescription = stringResource(viewModel.updateIconDesc(transaction.icon)),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(35.dp)
        )
        Column(
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = transaction.title,
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 14.sp,
                color = Color.Black,
            )
            Text(
                text = String.format("%s - %s", transaction.time, transaction.date),
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 10.sp,
                color = Color.Black,
            )
        }
        Text(
            text = viewModel.formatAmount(transaction.amount),
            textAlign = TextAlign.Right,
            fontFamily = montserratFontFamily,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp)
                .width(100.dp)
        )
    }
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier
            .width(320.dp)
            .height(1.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    val selectedDate = datePickerState.selectedDateMillis?.let {
        dateFormat.format(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }

            ) {
                Text(
                    text = "Select",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(
                    text = "Cancel",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    color = Color.White
                )
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

@Composable
fun DatePicker(imgSize: Int) {
    var date by remember {
        mutableStateOf("Open date picker dialog")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.calendaricon),
            contentDescription = stringResource(R.string.percentage_card_desc),
            modifier = Modifier
                .size(imgSize.dp)
                .clickable {
                    showDatePicker = true
                }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false }
        )
    }
}