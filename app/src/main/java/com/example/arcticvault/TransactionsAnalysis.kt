package com.example.arcticvault.com.example.arcticvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.arcticvault.R
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.montserratFontFamily

@Composable
fun TransactionsAnalysis() {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //Box for top banner
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.height(160.dp)
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
                            text = "Transactions Analysis",
                            fontFamily = montserratFontFamily,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 30.sp,
                            modifier = Modifier.width(190.dp)

                        )
                        Image(
                            painter = painterResource(R.drawable.backbutton),
                            contentDescription = stringResource(R.string.back_button_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Selections: ",
                    fontFamily = montserratFontFamily,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                ) {
                    Image(
                        painter = painterResource(R.drawable.percentagecard),
                        contentDescription = stringResource(R.string.percentage_card_desc),
                        modifier = Modifier
                            .height(38.dp)
                            .width(250.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {

                            },
                        ) {
                            Text(
                                text = stringResource(R.string.income),
                                fontFamily = montserratFontFamily,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                        Divider(
                            color = Color.White,
                            thickness = 1.dp,
                            modifier = Modifier
                                .width(1.dp)
                                .height(20.dp)
                        )
                        TextButton(
                            onClick = {

                            },

                            ) {
                            Text(
                                text = stringResource(R.string.expense),
                                fontFamily = montserratFontFamily,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Box {
                Image(
                    painter = painterResource(R.drawable.bluecard),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp, 60.dp)
                )
//                LazyRow(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceAround,
//                    modifier = Modifier
//                        .size(240.dp, 60.dp)
//                ) {
//                    items(categoryList) { category ->
//                        Spacer(Modifier.width(10.dp))
//                        Box {
//                            DisplayCategory(
//                                category = category,
//                                categoryClick = {
//
//                                })
//                            if () {
//                                Image(
//                                    painter = painterResource(R.drawable.confirmicon),
//                                    contentDescription = stringResource(R.string.back_button_desc),
//                                    alpha = 0.5f,
//                                    modifier = Modifier
//                                        .size(30.dp)
//                                        .align(Alignment.Center)
//                                )
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionsAnalysis() {
    ArcticVaultTheme {
        TransactionsAnalysis()
    }
}