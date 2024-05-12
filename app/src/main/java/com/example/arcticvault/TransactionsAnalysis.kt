package com.example.arcticvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.GroupBar
import co.yml.charts.ui.barchart.models.GroupBarChartData
import co.yml.charts.ui.barchart.models.GroupSeparatorConfig
import com.example.arcticvault.ui.AppViewModelProvider
import com.example.arcticvault.ui.TransactionsAnalysisUiState
import com.example.arcticvault.ui.TransactionsAnalysisViewModel
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.montserratFontFamily
import kotlin.math.roundToInt

object TransactionsAnalysisDestination {
    val route = "Analysis"
}

@Composable
fun TransactionsAnalysis(
    transactionsAnalysisViewModel: TransactionsAnalysisViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val transactionsAnalysisUiState by transactionsAnalysisViewModel.uiState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //Box for top banner
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.height(120.dp)
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
                            .padding(top = 10.dp)
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.categoryicon),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Select Category to Compare",
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            Box {
                Image(
                    painter = painterResource(R.drawable.bluecard),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp, 60.dp)
                )
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .size(240.dp, 60.dp)
                ) {
                    items(transactionsAnalysisViewModel.categoryList) { category ->
                        Spacer(Modifier.width(10.dp))
                        Box {
                            DisplayCategory(
                                category = category,
                                categoryClick = { })
                            if (false) {
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
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.bluecard),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .size(260.dp, 30.dp)
                            .clip(RoundedCornerShape(50))
                    )
                    Text(
                        text = "Compare by Year Only",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier
                    )
                }
                Checkbox(checked = false, onCheckedChange = { } )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(50)
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.datebackgroundactive),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(65.dp, 35.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                            Text(
                                text = "2023",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.datebackgroundinactive),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(65.dp, 35.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                            Text(
                                text = "JAN",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }
                    }
                    Text(
                        text = "Date 1",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(50)
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.datebackgroundactive),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(65.dp, 35.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                            Text(
                                text = "2023",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.datebackgroundinactive),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(65.dp, 35.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                            Text(
                                text = "JAN",
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }
                    }
                    Text(
                        text = "Date 2",
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier
                    )
                }
            }
            GroupBarChart(
                transactionsAnalysisViewModel = transactionsAnalysisViewModel,
                transactionsAnalysisUiState = transactionsAnalysisUiState
            )
        }
    }
}

@Composable
fun GroupBarChart(
    transactionsAnalysisViewModel: TransactionsAnalysisViewModel,
    transactionsAnalysisUiState: TransactionsAnalysisUiState
) {
    transactionsAnalysisViewModel.updateUiState(transactionsAnalysisViewModel.transactionsList, transactionsAnalysisViewModel.categoryList)
    val maxRange = transactionsAnalysisViewModel.findMaxRange().roundToInt()
    val barSize = 2
    val groupBarData = mutableListOf<GroupBar>()
    var i = 0
    for (transaction in transactionsAnalysisUiState.transactionList) {
        val barList = mutableListOf<BarData>()
        for (j in 0 until barSize) {
            val barValue = transaction.amount
            barList.add(
                BarData(
                    Point(
                        i.toFloat(),
                        barValue.toInt().toFloat()
                    ),
                    label = transaction.title,
                    description = "Bar at $i with label ${transaction.title} has value ${
                        String.format(
                            "%.2f", barValue
                        )
                    }"
                )
            )
        }
        groupBarData.add(GroupBar(i.toString(), barList))
        i++
    }
    val yStepSize = if(maxRange < 10) maxRange else 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .bottomPadding(5.dp)
        .startDrawPadding(48.dp)
        .labelData { index -> transactionsAnalysisUiState.transactionList[index].title }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val colorPaletteList = DataUtils.getColorPaletteList(barSize)
    val legendLabelList = mutableListOf<LegendLabel>()
    for (i in colorPaletteList.indices) {
        legendLabelList.add(
            LegendLabel(
                colorPaletteList[i],
                if (i == 0) {
                    "Date 1"
                }
                else "Date 2"
            )
        )
    }
    val legendsConfig = LegendsConfig(
        legendLabelList,
        gridColumnCount = 2,
        textStyle = TextStyle(fontFamily = montserratFontFamily)
    )
    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
    )
    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        groupSeparatorConfig = GroupSeparatorConfig(0.dp)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(400.dp)
    ) {
        Legends(
            legendsConfig = legendsConfig,
            modifier = Modifier
                .fillMaxWidth()
        )
        GroupBarChart(
            modifier = Modifier
                .height(300.dp),
            groupBarChartData = groupBarChartData
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionsAnalysis() {
    ArcticVaultTheme {
        TransactionsAnalysis()
    }
}