package com.example.arcticvault.ui.theme

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.Model.BudgetingInputModel

import com.example.arcticvault.R
import com.example.arcticvault.ui.theme.theme.BudgetingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Budgeting(
    onPreviousButton:() -> Unit,
    budgetingViewModel: BudgetingViewModel= viewModel(),
) {
    val budgetingUiState by budgetingViewModel.uiState.collectAsState()
    val budgetingFirst:BudgetingInputModel = budgetingUiState.budgeting
    var budgeting by remember { mutableStateOf("") }
    val monthlyBudgeting = monthly(budgeting.toDoubleOrNull() ?: 0.0)

    val monthlyExpense:Int = 5000
    val yearlyExpense:Int = 5000


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
    Image(
        painter = painterResource(R.drawable.budgeting_1),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Yearly Budgeting",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(5.dp))
/*            PercentageBarForBudgeting(
//                percentage = percentageOfMonthly,
            )*/
            Spacer(modifier = Modifier.height(5.dp))
            Row(){
                Text(
                    text = "RM$yearlyExpense of ",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                )
                TextField(
                    value = budgetingViewModel.formatAmount(budgetingFirst.yearlyBudgeting),
                    onValueChange = { budgetingViewModel.updateUiState(budgetingFirst.copy(yearlyBudgeting = budgetingViewModel.updateAmount(it))) },
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
            }


            Text(
                text = "Monthly Budgeting",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(5.dp))
/*            val percentage1 = percentageOfYearly // Example percentage value*//*
            PercentageBarForBudgeting(
                percentage = percentage1,
            )*/
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "RM$monthlyExpense of RM$monthlyBudgeting",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Monthly Budget",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            PieChart(
                data = mapOf(
                    Pair("Sample-1", 150),
                    Pair("Sample-2", 120),
                    Pair("Sample-3", 110),
                    Pair("Sample-4", 170),
                    Pair("Sample-5", 120),
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Yearly Budget",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            PieChart(
                data = mapOf(
                    Pair("Sample-1", 150),
                    Pair("Sample-2", 120),
                    Pair("Sample-3", 110),
                    Pair("Sample-4", 170),
                    Pair("Sample-5", 120),
                )
            )
        }



@Composable
fun PercentageBarForBudgeting(percentage: Double) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = (percentage / 100f).toFloat(), // Convert percentage to a value between 0 and 1
            modifier = Modifier
                .width(200.dp)
                .size(10.dp)
        )
        Spacer(modifier = Modifier.width(13.dp)) // Add space between LinearProgressIndicator and Text
        Text(
            text = String.format("%.1f", percentage),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
fun PieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 40.dp,
    chartBarWidth: Dp = 30.dp,
    animDuration: Int = 1000,
) {

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    // To set the value of each Arc according to
    // the value given in the data, we have used a simple formula.
    // For a detailed explanation check out the Medium Article.
    // The link is in the about section and readme file of this GitHub Repository
    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    // add the colors as per the number of data(no. of pie chart entries)
    // so that each data will get a color
    val colors = listOf(
        Purple40,
        Purple80,
        Color.Black,
        Color.Red,
        Color.Blue
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    // it is the diameter value of the Pie
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // to play the animation only once when the function is Created or Recomposed
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Pie Chart using Canvas Arc
        Row(
            modifier = Modifier,
        ) {
            Canvas(
                modifier = Modifier
                    .offset { IntOffset.Zero }
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ) {
                // draw each Arc for each data entry in Pie Chart
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }
        Spacer(modifier = Modifier.width(40.dp))
        // To see the data in more structured way
        // Compose Function in which Items are showing data
        DetailsPieChart(
            data = data,
            colors = colors
        )
    }
}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>,
    colors: List<Color>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // create the data items
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index]
            )
        }

    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 30.dp,
    color: Color
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(10.dp)
                )
                .size(height)
        )

        Row(modifier = Modifier) {
            Row {
                Text(
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }

        }
    }
}
