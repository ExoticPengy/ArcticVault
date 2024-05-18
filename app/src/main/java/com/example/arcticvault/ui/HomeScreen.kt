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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Reminder

object HomeDestination {
    val route = "Home"
}

@Composable
fun HomeScreen(
    onTransactionClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onGoalClick: () -> Unit,
    onReminderClick: () -> Unit,
    onDebtClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String = "User"
) {
    Surface(
        Modifier.fillMaxSize(),
        color = Color(231, 245, 255)
    ) {

    }
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0, 0, 0)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Arctic Vault",
                    color = Color.White,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        GreetingMessage(modifier = Modifier, name = name)
        HomeScreenUI(
            onTransactionClick = { onTransactionClick() },
            onBudgetClick = { onBudgetClick() },
            onAnalysisClick = { onAnalysisClick() },
            onGoalClick = { onGoalClick() },
            onReminderClick = { onReminderClick() },
            onDebtClick = {}
        )
        UpcomingBills()
    }

}

//User profile and user name
@Composable
fun GreetingMessage(
    modifier: Modifier = Modifier,
    name: String
) {
    Surface(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        color = Color(231, 245, 255)
    ) {
        Row(modifier = modifier.fillMaxWidth())
        {
            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = "Profile Picture"
            )

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    text = "Welcome ",
                    fontSize = 40.sp
                )
                Text(
                    modifier = Modifier
                        .padding(),
                    text = " $name",
                    fontSize = 40.sp
                )
            }

        }
    }
}

//All features Ui
@Composable
fun HomeScreenUI(
    onTransactionClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onGoalClick: () -> Unit,
    onReminderClick: () -> Unit,
    onDebtClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color(244, 245, 255)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
        ) {
            Text(
                text = "Features",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FeatureItem(
                    R.drawable.trans_icon,
                    "Transactions",
                    onClick = { onTransactionClick() })
                FeatureItem(R.drawable.budget_icon, "Budgeting", onClick = { onBudgetClick() })
                FeatureItem(
                    R.drawable.bill_icon,
                    "Bills & Payments",
                    onClick = { onReminderClick() })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FeatureItem(R.drawable.goal_icon, "Financial Goals", onClick = { onGoalClick() })
                FeatureItem(
                    R.drawable.t_analysis_icon,
                    "Transactions Analysis",
                    isLonger = true,
                    onClick = { onAnalysisClick() })
                FeatureItem(
                    R.drawable.loan_icon,
                    "Loan and Debt Management",
                    isLonger = true,
                    onClick = { onDebtClick() })
            }
        }
    }
}

@Composable
fun FeatureItem(icon: Int, text: String, isLonger: Boolean = false, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(
                    110.dp,
                    height = if (isLonger) 140.dp else 120.dp
                )
                .background(Color.White)
                .padding(bottom = 10.dp),

            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 90.dp)
            )
        }
    }
}


//upcoming bill
@Composable
fun UpcomingBills(reminderViewModel: ReminderViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState by reminderViewModel.uiState.collectAsState()

    val upcomingReminders = uiState.upcomingReminders
    val lateReminders = uiState.lateReminders

    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(),
        color = Color(0xFFE7F5FF)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Reminders",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(
                    count = lateReminders.size,
                    key = { index -> lateReminders[index].id },
                    itemContent = { index ->
                        val reminder = lateReminders[index]
                        ReminderCard(reminder)
                    }
                )
                items(

                    count = upcomingReminders.size,
                    key = { index -> upcomingReminders[index].id },
                    itemContent = { index ->
                        val reminder = upcomingReminders[index]
                        ReminderCard(reminder)
                    }
                )

            }
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    val textColor = if (reminder.status == "Late") Color.Red else Color.Black

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (modifier = Modifier
            ) {
                Text(text = reminder.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = reminder.date, fontSize = 14.sp, color = Color.Gray)
            }
            Text(text = "RM ${reminder.amount}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor )
        }
    }
}
