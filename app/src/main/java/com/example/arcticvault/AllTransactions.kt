package com.example.arcticvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.data.Category
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
    val transactionList: List<Transaction> = allTransactionsUiState.transactionList

    Surface(modifier = Modifier.fillMaxSize()) {
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
                            text = stringResource(allTransactionsViewModel.typeFilter),
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
                                        allTransactionsViewModel.changeTypeFilter(R.string.income)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Text(
                                    text = stringResource(R.string.income),
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
                                        allTransactionsViewModel.changeTypeFilter(R.string.expense)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Text(
                                    text = stringResource(R.string.expense),
                                    fontFamily = montserratFontFamily,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    TextField(
                        value = allTransactionsViewModel.titleFilter,
                        onValueChange = { allTransactionsViewModel.titleFilter = it },
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
                        placeholder = {
                            Text(
                                text = "Search",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        singleLine = true,
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
                        //Date Picker
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(R.drawable.calendaricon),
                                contentDescription = stringResource(R.string.percentage_card_desc),
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable {
                                        allTransactionsViewModel.showDatePicker = true
                                    }
                            )
                        }

                        if (allTransactionsViewModel.showDatePicker) {
                            DatePickerDialog(
                                onDateSelected = { allTransactionsViewModel.dateFilter = it },
                                onDismiss = { allTransactionsViewModel.showDatePicker = false },
                                onClear = { allTransactionsViewModel.dateFilter = "Select a date" },
                                dismissButtonText = R.string.clear
                            )
                        }

                        Spacer(Modifier.width(30.dp))
                        Image(
                            painter = painterResource(R.drawable.filtericon),
                            contentDescription = stringResource(R.string.percentage_card_desc),
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    allTransactionsViewModel.showFilterDialog = true
                                }
                        )
                        if (allTransactionsViewModel.showFilterDialog) {
                            FilterDialog(
                                allTransactionsViewModel = allTransactionsViewModel ,
                                onSelect = { allTransactionsViewModel.selectedCategoryId = it },
                                onDismissRequest = { allTransactionsViewModel.showFilterDialog = false }
                            )
                        }
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Total",
                        color = Color.White,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp)
                    )

                    Spacer(Modifier.width(70.dp))

                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )

                    Text(
                        text = allTransactionsViewModel.calculateTotal(transactionList),
                        color = Color.White,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(155.dp)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(transactionList) { transaction ->
                    if (
                        allTransactionsViewModel.typeFilter == transaction.type &&
                        allTransactionsViewModel.checkTitleFilter(transaction.title) &&
                        allTransactionsViewModel.checkDateFilter(transaction.date) &&
                        allTransactionsViewModel.checkCategoryFilter(transaction.categoryId)
                        )
                        TransactionTexts(onTransactionClick = { onTransactionClick(it) }, transaction = transaction, viewModel = allTransactionsViewModel)
                }
            }
        }
    }
}

@Composable
fun TransactionTexts(
    onTransactionClick: (Int) -> Unit,
    transaction: Transaction,
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = String.format("%s - %s", transaction.time, transaction.date),
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 10.sp,
                color = Color.Black
            )
        }
        Text(
            text = viewModel.formatAmount(transaction.amount),
            textAlign = TextAlign.Right,
            fontFamily = montserratFontFamily,
            fontSize = 16.sp,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 10.dp)
                .width(110.dp)
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
    onDismiss: () -> Unit,
    onClear: () -> Unit = {},
    dismissButtonText: Int
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
                onClear()
                onDismiss()
            }) {
                Text(
                    text = stringResource(dismissButtonText),
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
fun FilterDialog(
    allTransactionsViewModel: AllTransactionsViewModel,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Filters",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .size(200.dp, 300.dp)
                ) {
                    items(allTransactionsViewModel.categoryList) {category ->
                        Spacer(Modifier.height(20.dp))
                        Box {
                            Categories(category = category, categoryClick = { onSelect(it) })
                            if (allTransactionsViewModel.selectedCategoryId == category.id) {
                                Image(
                                    painter = painterResource(R.drawable.confirmicon),
                                    contentDescription = stringResource(R.string.back_button_desc),
                                    alpha = 0.5f,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            allTransactionsViewModel.selectedCategoryId = allTransactionsViewModel.selectedCategoryFilter
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                    TextButton(
                        onClick = {
                            allTransactionsViewModel.selectedCategoryFilter = 0
                            allTransactionsViewModel.selectedCategoryId = 0
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.clear),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.width(80.dp)
                        )
                    }

                    TextButton(
                        onClick = {
                            allTransactionsViewModel.selectedCategoryFilter = allTransactionsViewModel.selectedCategoryId
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.width(80.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Categories(category: Category, categoryClick: (Int) -> Unit) {
    Text(
        text = category.title,
        textAlign = TextAlign.Center,
        fontFamily = montserratFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier
            .background(color = Color(category.color.toULong()), shape = RoundedCornerShape(50))
            .padding(5.dp)
            .clickable { categoryClick(category.id) }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAllTransactions() {
//    ArcticVaultTheme {
//        AllTransactions(onTransactionClick = {}, onBackButtonClick = {})
//    }
//}