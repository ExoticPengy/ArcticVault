package com.example.arcticvault

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.montserratFontFamily

@Composable
fun AllTransaction() {
    Surface(modifier = Modifier.fillMaxSize()) {
        var incomeOrExpense by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
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

                                }
                        )
                        Text(
                            text = "Income",
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
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                onClick = {},
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
                                onClick = {},
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
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        leadingIcon = { Icon(painter = painterResource(R.drawable.magnifyingglassicon), contentDescription = null) },
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                ),
                        shape = RoundedCornerShape(40)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllTransactionPreview() {
    ArcticVaultTheme {
        AllTransaction()
    }
}