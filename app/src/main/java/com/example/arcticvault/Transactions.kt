package com.example.arcticvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
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
            //Box for top banner
            Box() {
                Image(
                    painter = painterResource(R.drawable.topbanner),
                    contentDescription = stringResource(R.string.blue_topbanner_desc),
                    modifier = Modifier
                        .requiredHeight(330.dp)
                        .fillMaxWidth()

                )
                Row(
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
                    Spacer(Modifier.padding(start = 30.dp))
                    Text(
                        text = stringResource(R.string.transaction_screen_title),
                        fontFamily = montserratFontFamily,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.padding(start = 30.dp))
                    Image(
                        painter = painterResource(R.drawable.backbutton),
                        contentDescription = stringResource(R.string.back_button_desc),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .requiredSize(35.dp)
                            .clickable {

                            }
                    )
                }

                //Transaction card
                Box(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .align(Alignment.TopCenter)
                        .padding(top = 150.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.transactioncard),
                        contentDescription = stringResource(R.string.transaction_card_desc),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .requiredHeight(170.dp)
                            .requiredWidth(300.dp)
                    )
                    Column(
                    ) {
                        Text(
                            text = "Pengu Company",
                            fontFamily = montserratFontFamily,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp)
                        )
                        Spacer(Modifier.padding(bottom = 5.dp))
                        Divider(
                            color = Color.White,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    ArcticVaultTheme {
        Transaction()
    }
}