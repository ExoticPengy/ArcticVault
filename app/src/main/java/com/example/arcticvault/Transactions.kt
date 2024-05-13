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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.ui.AppViewModelProvider
import com.example.arcticvault.ui.TransactionsViewModel
import com.example.arcticvault.ui.theme.montserratFontFamily

object TransactionsDestination {
    val route = "Transactions"
}

@Composable
fun Transactions(
    onAddExpenseClick: () -> Unit,
    onAddIncomeClick: () -> Unit,
    onViewAllClick: () -> Unit,
    transactionsViewModel: TransactionsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val transactionsUiState by transactionsViewModel.transactionsUiState.collectAsState()
    val transactionList: List<Transaction> = transactionsUiState.transactionList
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            //top banner and transactions card
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

                                }
                        )
                        Text(
                            text = stringResource(R.string.transaction_screen_title),
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

                    //Transaction card
                    Box(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .padding(top = 45.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.transactioncard),
                            contentDescription = stringResource(R.string.transaction_card_desc),
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .height(180.dp)
                                .width(310.dp)
                        )

                        //Company name, divider, and add transactions row
                        Column {
                            Text(
                                text = "Pengu Company",
                                fontFamily = montserratFontFamily,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                            )
                            Divider(
                                color = Color.White,
                                thickness = 1.dp
                            )

                            //Add Transactions Row
                            Row(
                                modifier = Modifier
                                    .padding(top = 35.dp)
                            ) {
                                Text(
                                    text = "Add\nTransactions",
                                    textAlign = TextAlign.Center,
                                    fontFamily = montserratFontFamily,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                                )
                                Divider(
                                    color = Color.White,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 12.dp)
                                        .width(1.dp)
                                        .height(30.dp)
                                )

                                //Add Income Button
                                Column(
                                    modifier = Modifier
                                        .padding(start = 25.dp)
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.income),
                                        contentDescription = stringResource(R.string.income_desc),
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(45.dp)
                                            .clickable {
                                                onAddIncomeClick()
                                            }
                                    )
                                    Text(
                                        text = "Income",
                                        textAlign = TextAlign.Center,
                                        fontFamily = montserratFontFamily,
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                    )
                                }

                                //Add Expense Button
                                Column(
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.expense),
                                        contentDescription = stringResource(R.string.expense_desc),
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(45.dp)
                                            .clickable {
                                                onAddExpenseClick()
                                            }
                                    )
                                    Text(
                                        text = "Expense",
                                        textAlign = TextAlign.Center,
                                        fontFamily = montserratFontFamily,
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            //Profit card
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .width(IntrinsicSize.Min)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
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
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        progress = (transactionsViewModel.calculateProfit(transactionList, false)/100).toFloat(),
                        color = Color.Green,
                        trackColor = Color.DarkGray,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Text(
                        text = "Income",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Column {
                        Text(
                            text = transactionsViewModel.formatAmount(
                                transactionsViewModel.calculateProfit(transactionList, true)
                            ),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = Color.White
                        )
                        Row {
                            Text(
                                text = "${transactionsViewModel.positiveOrNegative(transactionsViewModel.profitIsPositive)}${transactionsViewModel.calculateProfit(transactionList, false)}%",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = transactionsViewModel.changeColor(transactionsViewModel.profitIsPositive, true),
                                modifier = Modifier.width(65.dp)
                            )
                            Text(
                                text = " vs last year",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }

            }

            Spacer(Modifier.height(15.dp))

            //Loss card
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .width(IntrinsicSize.Min)
                    .align(Alignment.CenterHorizontally)
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
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        progress = (transactionsViewModel.calculateLoss(transactionList, false)/100).toFloat(),
                        color = Color.Red,
                        trackColor = Color.DarkGray,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Text(
                        text = "Expense",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Column {
                        Text(
                            text = transactionsViewModel.formatAmount(transactionsViewModel.calculateLoss(transactionList, true)),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = Color.White
                        )
                        Row {
                            Text(
                                text = "${transactionsViewModel.positiveOrNegative(transactionsViewModel.lossIsPositive)}${transactionsViewModel.calculateLoss(transactionList, false)}%",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = transactionsViewModel.changeColor(transactionsViewModel.lossIsPositive, false),
                                modifier = Modifier.width(65.dp)
                            )
                            Text(
                                text = " vs last year",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            //Transactions Section
            Text(
                text = "Recent transactions",
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 40.dp, top = 20.dp)
            )

            //3 Recent Transactions
            for (items in 0..2) {
                if (transactionList.getOrNull(items) != null) {
                    RecentTransactionTexts(
                        icon = transactionList[items].icon,
                        iconDesc = transactionsViewModel.updateIconDesc(transactionList[items].icon),
                        title = transactionList[items].title,
                        time = transactionList[items].time,
                        date = transactionList[items].date,
                        amount = transactionsViewModel.formatAmount(transactionList[items].amount)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.viewalltransactionsbutton),
                contentDescription = stringResource(R.string.expense_desc),
                modifier = Modifier
                    .height(45.dp)
                    .width(250.dp)
                    .padding(start = 40.dp)
                    .clickable {
                        onViewAllClick()
                    }
            )
        }
    }
}

@Composable
fun RecentTransactionTexts(icon: Int, iconDesc: Int, title: String, time: String, date: String, amount: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 40.dp, top = 8.dp, bottom = 8.dp)
            .width(310.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = stringResource(iconDesc),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(35.dp)
        )
        Column(
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$time - $date",
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 10.sp,
                color = Color.Black
            )
        }
        Text(
            text = amount,
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
            .width(350.dp)
            .height(1.dp)
            .padding(start = 40.dp)
    )
}