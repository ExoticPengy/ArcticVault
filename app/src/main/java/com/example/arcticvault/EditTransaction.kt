package com.example.arcticvault

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.model.TransactionModel
import com.example.arcticvault.ui.AppViewModelProvider
import com.example.arcticvault.ui.EditTransactionViewModel
import com.example.arcticvault.ui.theme.montserratFontFamily
import kotlinx.coroutines.launch

@Composable
fun EditTransaction(
    editTransactionViewModel: EditTransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val editTransactionUiState by editTransactionViewModel.uiState.collectAsState()
    val transaction: TransactionModel = editTransactionUiState.transaction
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //Box for top banner
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.height(280.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.topbanner),
                    contentDescription = stringResource(R.string.blue_topbanner_desc),
                    modifier = Modifier
                        .wrapContentHeight(unbounded = true, align = Alignment.Bottom)
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

                                }
                        )
                        Text(
                            text = "Edit Transaction",
                            fontFamily = montserratFontFamily,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(R.drawable.backbutton),
                            contentDescription = stringResource(R.string.back_button_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        editTransactionViewModel.saveTransaction(editTransactionUiState)
                                    }
                                }
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(transaction.icon),
                            contentDescription = stringResource(editTransactionViewModel.updateIconDesc(transaction.icon)),
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    if (transaction.icon == R.drawable.income) {
                                        editTransactionViewModel.updateUiState(
                                            transaction.copy(
                                                icon = R.drawable.expense,
                                                type = R.string.expense
                                            )
                                        )
                                    } else {
                                        editTransactionViewModel.updateUiState(
                                            transaction.copy(
                                                icon = R.drawable.income,
                                                type = R.string.income
                                            )
                                        )
                                    }
                                }
                        )
                        Text(
                            text = stringResource(transaction.type) ,
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                    TextField(
                        value = transaction.title,
                        onValueChange = { editTransactionViewModel.updateUiState(transaction.copy(title = it)) },
                        placeholder = {
                            Text(
                                text = "Transaction",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.editicon),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        maxLines = 1,
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.Transparent
                            ),
                        modifier = Modifier
                            .widthIn(1.dp, 300.dp)
                    )
                    TextField(
                        value = editTransactionViewModel.formatAmount(transaction.amount),
                        onValueChange = { editTransactionViewModel.updateUiState(transaction.copy(amount = editTransactionViewModel.updateAmount(it))) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        maxLines = 1,
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.Transparent
                            ),
                        modifier = Modifier
                            .widthIn(1.dp, 280.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .fillMaxHeight()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.calendaricon),
                            contentDescription = stringResource(R.string.percentage_card_desc),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    editTransactionViewModel.showDatePicker = true
                                }
                        )
                    }

                    if (editTransactionViewModel.showDatePicker) {
                        DatePickerDialog(
                            onDateSelected = { editTransactionViewModel.updateUiState(transaction.copy(date = it)) },
                            onDismiss = { editTransactionViewModel.showDatePicker = false }
                        )
                    }
                    Text(
                        text = transaction.date,
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TestingTime(transaction = transaction)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.categoryicon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "Set Category",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.bluecard),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp, 120.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.attachmenticon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "Attachments",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.bluecard),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp, 120.dp)
                )
                Spacer(Modifier.height(5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestingTime(
    transaction: TransactionModel,
    editTransactionViewModel: EditTransactionViewModel = viewModel()
) {
    val timePickerState = rememberTimePickerState(is24Hour = true)

    Image(
        painter = painterResource(R.drawable.clockicon),
        contentDescription = null,
        modifier = Modifier
            .size(30.dp)
            .clickable {
                editTransactionViewModel.showTimePicker = true
            }
    )
    Text(
        text = transaction.time,
        textAlign = TextAlign.Center,
        fontFamily = montserratFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 20.dp)
    )

    if (editTransactionViewModel.showTimePicker) {
        TimePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    onClick = {
                        editTransactionViewModel.updateUiState(
                            transaction.copy(
                                time = timePickerState.hour.toString() + ":" + timePickerState.minute.toString()
                            ))
                        editTransactionViewModel.showTimePicker = false
                    }
                ) { Text(
                    text = "OK",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        editTransactionViewModel.showTimePicker = false
                    }
                ) { Text(
                    text = "Cancel",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)

        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton()
                    confirmButton()
                }
            }
        }
    }
}

