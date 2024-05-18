package com.example.arcticvault.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.EditGoals

object FinancialGoalsDestination {
    val route = "FinancialGoals"
}

@Composable
fun Finance(
    onGoalClick: (Int) -> Unit,
    onEditGoalsButton: () -> Unit,
    onPreviousButton: () -> Unit,
    financialGoalsViewModel: FinancialGoalsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val financialGoalsUiState by financialGoalsViewModel.financialGoalsUiState.collectAsState()
    val financialGoalsList1: List<EditGoals> = financialGoalsUiState.financialGoalsList
    val context = LocalContext.current

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            errorMessage = null
        }
    }
    //Top Banner
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(330.dp)
                .fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 40.dp)
        ) {
            TextButton(onClick = { onPreviousButton() }) {
                Image(
                    painter = painterResource(R.drawable.backbuttoncropped),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                )
            }
            Text(
                text = "Financial Goals",
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.2f),
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(R.drawable.default_profile_pic),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(50))
            )
        }
    }
    //Milestones Achieved
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Milestones achieved",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 125.dp)
        )
        val percentage = 0f // Example percentage value
        PercentageBar(
            percentage = percentage
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Milestones:",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 190.dp)
            )
            Spacer(modifier = Modifier.width(9.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.milestonesbackground),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 190.dp)
                        .size(30.dp)
                )
                Text(
                    text = financialGoalsViewModel.calculateTotal(financialGoalsList1).toString(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 190.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Completed Milestone:",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 220.dp)
            )
            Spacer(modifier = Modifier.width(9.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.milestonesbackground),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 220.dp)
                        .size(30.dp)
                )
                Text(
                    text = "0",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 220.dp)
                )
            }
        }
        //Create A New Goals
        Column {
            Button(
                onClick = {
                    if (financialGoalsList1.size >= 3) {
                        errorMessage = "Cannot add more goals, already have 3 goals!"
                    } else if (financialGoalsList1.getOrNull(0) == null || financialGoalsList1.getOrNull(
                            1
                        ) == null || financialGoalsList1.getOrNull(2) == null
                    ) {
                        onEditGoalsButton()
                    }
                },
                modifier = Modifier
                    .padding(top = 275.dp)
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.Goal))
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Goals Edit",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 40.dp)
        )
    }
    //Edit The Goals
    val financialGoalsList: List<EditGoals> = financialGoalsUiState.financialGoalsList
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        if (financialGoalsList.getOrNull(0) != null) {
            AllFinancialGoals(
                onGoalClick = { onGoalClick(it) },
                goalNumber = 0,
                title = financialGoalsList[0].title,
                amount = financialGoalsViewModel.formatAmount(financialGoalsList[0].amount),
                milestones = financialGoalsList[0].milestones
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(320.dp))
        if (financialGoalsList.getOrNull(1) != null) {
            AllFinancialGoals(
                onGoalClick = { onGoalClick(it) },
                goalNumber = 1,
                title = financialGoalsList[1].title,
                amount = financialGoalsViewModel.formatAmount(financialGoalsList[1].amount),
                milestones = financialGoalsList[1].milestones
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(550.dp))
        if (financialGoalsList.getOrNull(2) != null) {
            AllFinancialGoals(
                onGoalClick = { onGoalClick(it) },
                goalNumber = 2,
                title = financialGoalsList[2].title,
                amount = financialGoalsViewModel.formatAmount(financialGoalsList[2].amount),
                milestones = financialGoalsList[2].milestones
            )
        }

    }
}

@Composable
fun PercentageBar(percentage: Float) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = percentage / 100f, // Convert percentage to a value between 0 and 1
            modifier = Modifier
                .width(200.dp)
                .padding(top = 160.dp)
                .size(10.dp)
        )
        Spacer(modifier = Modifier.width(13.dp)) // Add space between LinearProgressIndicator and Text
        Text(
            text = "0%",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 160.dp)
        )
    }
}

@Composable
fun AllFinancialGoals(
    onGoalClick: (Int) -> Unit,
    goalNumber: Int,
    title: String,
    amount: String,
    milestones: Int
) {
    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.milestones),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp, 100.dp)
                    .clickable { onGoalClick(goalNumber) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 70.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = "Title:$title",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 80.dp)
                )
            }
            Row {
                Text(
                    text = "Amount:$amount",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 80.dp)
                )
            }
            Row {
                Text(
                    text = "Milestones:$milestones",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 80.dp)
                )
            }
        }
    }
}
