package com.example.arcticvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.montserratFontFamily

class Transactions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArcticVaultTheme {
                Transaction()
            }
        }
    }
}

@Composable
fun Transaction() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
        ) {
            //Box for top banner and transactions card
            Box() {
                Image(
                    painter = painterResource(R.drawable.topbanner),
                    contentDescription = stringResource(R.string.blue_topbanner_desc),
                    modifier = Modifier
                        .requiredHeight(330.dp)
                        .fillMaxWidth()

                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(R.drawable.backbutton),
                        contentDescription = stringResource(R.string.back_button_desc),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .requiredSize(35.dp)
                            .clickable {

                            }
                    )
                    Spacer(Modifier.width(30.dp))
                    Text(
                        text = stringResource(R.string.transaction_screen_title),
                        fontFamily = montserratFontFamily,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(30.dp))
                    Image(
                        painter = painterResource(R.drawable.backbutton),
                        contentDescription = stringResource(R.string.back_button_desc),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .requiredSize(35.dp)
                    )
                }

                //Transaction card
                Box(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .align(Alignment.TopCenter)
                        .padding(top = 135.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.transactioncard),
                        contentDescription = stringResource(R.string.transaction_card_desc),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .requiredHeight(180.dp)
                            .requiredWidth(310.dp)
                    )

                    //Company name, divider, and add transactions row
                    Column(
                    ) {
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
                                        .requiredSize(45.dp)
                                        .clickable {

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
                                        .requiredSize(45.dp)
                                        .clickable {

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

            //Profit card
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .width(IntrinsicSize.Min)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.percentagecard),
                    contentDescription = stringResource(R.string.percentage_card_desc),
                    modifier = Modifier
                        .requiredHeight(50.dp)
                        .requiredWidth(310.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.width(10.dp))
                    CircularProgressIndicator(
                        progress = 0.7f,
                        color = Color.Green,
                        trackColor = Color.DarkGray,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Income",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.width(20.dp))
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Column() {
                        Text(
                            text = "RM8901",
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Row() {
                            Text(
                                text = "+78%",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                color = Color.Green
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
                        .requiredHeight(50.dp)
                        .requiredWidth(310.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.width(10.dp))
                    CircularProgressIndicator(
                        progress = 0.7f,
                        color = Color.Red,
                        trackColor = Color.DarkGray,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Expense",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.width(10.dp))
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    Column() {
                        Text(
                            text = "RM3678",
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Row() {
                            Text(
                                text = "-27%",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 12.sp,
                                color = Color.Red
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

            //5 Recent Transactions
            RecentTransactionTexts(R.drawable.expense, "Facility Repairs", "02.15 PM", "March 8", 1000.0)
            RecentTransactionTexts(R.drawable.income, "Investors", "02.15 PM", "March 8", 3000.0)
            RecentTransactionTexts(R.drawable.income, "Sales", "02.15 PM", "March 8", 70.0)
            RecentTransactionTexts(R.drawable.expense, "Office Supplies", "02.15 PM", "March 8", 130.30)
            RecentTransactionTexts(R.drawable.income, "New Subscriptions", "02.15 PM", "March 8", 500.0)

            Spacer(Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.viewalltransactionsbutton),
                contentDescription = stringResource(R.string.expense_desc),
                modifier = Modifier
                    .requiredHeight(45.dp)
                    .requiredWidth(250.dp)
                    .padding(start = 40.dp)
            )
        }
    }
}

@Composable
fun RecentTransactionTexts(icon: Int, transaction: String, time: String, date: String, amount: Double) {
    val amountString = String.format("%.2f", amount)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 40.dp, top = 8.dp, bottom = 8.dp)
            .width(310.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.expense_desc),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .requiredSize(35.dp)
        )
        Column(
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = transaction,
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 14.sp,
                color = Color.Black,
            )
            Text(
                text = "$time - $date",
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 10.sp,
                color = Color.Black,
            )
        }
        Text(
            text = "RM$amountString",
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
            .width(350.dp)
            .height(1.dp)
            .padding(start = 40.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    ArcticVaultTheme {
        Transaction()
    }
}