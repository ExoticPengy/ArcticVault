package com.example.arcticvault.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.EditGoals
import com.example.arcticvault.ui.theme.montserratFontFamily

object EditGoalsDestination {
    val route = "EditGoals"
    val goalIdArg = "goalId"
    val routeWithArgs = "$route/{$goalIdArg}"
}

@Composable
fun Edit_Goals(
    onPreviousButton: () -> Unit,
    onEditGoalsButton: (Int) -> Unit,
    onTransactionsButton: () -> Unit,
    editGoalsViewModel: EditGoalsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    transactionsViewModel: TransactionsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    editTransactionViewModel: EditTransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val transactionsUiState by transactionsViewModel.transactionsUiState.collectAsState()
    val editGoalsUiState by editGoalsViewModel.editGoalsUiState.collectAsState()
    val editTransactionUiState by editTransactionViewModel.uiState.collectAsState()
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
            Image(
                painter = painterResource(R.drawable.backbuttoncropped),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .clickable { onPreviousButton() }
            )
            Text(
                text = "Edit Goals",
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
    //Display The Content Of The Goals
    val editGoalsList: List<EditGoals> = editGoalsUiState.editGoalsList
    if (editGoalsViewModel.numberChanges == 0 && editGoalsList.getOrNull(0) != null) {
        AllEditGoals(
            title = editGoalsList[0].title,
            amount = editGoalsViewModel.formatAmount(editGoalsList[0].amount),
            milestones = editGoalsList[0].milestones,
            startDate = editGoalsList[0].startDate,
            endDate = editGoalsList[0].endDate,
            amountGetDivided = editGoalsViewModel.formatAmount(editGoalsList[0].amountGetDivided),
            dateOfDivided = editGoalsList[0].dateOfDivided,
        )
    } else if (editGoalsViewModel.numberChanges == 1 && editGoalsList.getOrNull(1) != null) {
        AllEditGoals(
            title = editGoalsList[1].title,
            amount = editGoalsViewModel.formatAmount(editGoalsList[1].amount),
            milestones = editGoalsList[1].milestones,
            startDate = editGoalsList[1].startDate,
            endDate = editGoalsList[1].endDate,
            amountGetDivided = editGoalsViewModel.formatAmount(editGoalsList[1].amountGetDivided),
            dateOfDivided = editGoalsList[1].dateOfDivided,
        )
    } else if (editGoalsViewModel.numberChanges == 2 && editGoalsList.getOrNull(2) != null) {
        AllEditGoals(
            title = editGoalsList[2].title,
            amount = editGoalsViewModel.formatAmount(editGoalsList[2].amount),
            milestones = editGoalsList[2].milestones,
            startDate = editGoalsList[2].startDate,
            endDate = editGoalsList[2].endDate,
            amountGetDivided = editGoalsViewModel.formatAmount(editGoalsList[2].amountGetDivided),
            dateOfDivided = editGoalsList[2].dateOfDivided,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(R.drawable.edit),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable { onEditGoalsButton(editGoalsViewModel.numberChanges ?: 0) }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Image(
                painter = painterResource(R.drawable.categoryicon),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 60.dp)
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Set Category to Track",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }
    //Transaction And Category
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Box {
            Image(
                painter = painterResource(R.drawable.bluecard),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(300.dp))
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .size(240.dp, 60.dp)
                ) {
                    val categoryList: List<Category> = editGoalsViewModel.categoryList
                    items(categoryList.size) { index ->
                        Spacer(Modifier.width(20.dp))
                        Box {
                            DisplayGoalCategory(
                                category = categoryList[index],
                                categoryClick = {
                                    editGoalsViewModel.selectedCategoryId = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    //Display Transaction And Category
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(290.dp))
        Text(
            text = "Related Transactions:",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 60.dp)
        )
    }
    val transactionList = transactionsUiState.transactionList
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(480.dp))
        for (items in 0..12) {
            if (transactionList.getOrNull(items)?.categoryId == editGoalsViewModel.selectedCategoryId) {
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
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(720.dp))
        Image(
            painter = painterResource(R.drawable.viewallrelatedtransactions),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 60.dp)
                .size(200.dp)
                .clickable { onTransactionsButton() }
        )
    }
}

@Composable
fun AllEditGoals(
    title: String,
    amount: String,
    milestones: Int,
    startDate: String,
    endDate: String,
    amountGetDivided: String,
    dateOfDivided: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(R.drawable.milestones_bigger),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = amount,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Column {
            Text(
                text = "Milestones",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = milestones.toString(),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(135.dp))
        Text(
            text = "Date:",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 90.dp)

        )
        Image(
            painter = painterResource(R.drawable.calendaricon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 100.dp)
                .size(20.dp)
        )
        Text(
            text = "Start Date:\n$startDate\n-\nEnd Date:\n$endDate",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 65.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 55.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(195.dp))
        Image(
            painter = painterResource(R.drawable.milestones),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(300.dp))
        Column {
            Text(
                text = "Next Milestones:",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = "1 of $milestones",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = amountGetDivided,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(340.dp))
        Text(
            text = dateOfDivided,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 60.dp)
        )
    }
}

@Composable
fun DisplayGoalCategory(category: Category, categoryClick: (Int) -> Unit) {
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












