package com.example.arcticvault.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository

@Composable
fun ReminderScreen(onBackButtonClick: () -> Unit) {
    Column {
        ReminderTopUi(onBackButtonClick = { onBackButtonClick() } )
        BillScreen()
    }
}

@Composable
fun ReminderTopUi(onBackButtonClick: () -> Unit){
    var search by remember { mutableStateOf("") }

    var showDialog by remember {
        mutableStateOf(false)
    }

    Surface() {
        Image(
            painter = painterResource(R.drawable.topbannercropped),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.backbutton),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { onBackButtonClick() }
                )
                Text(
                    text = "Bills and Payments",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .width(IntrinsicSize.Min)
                )
                Image(
                    painter = painterResource(R.drawable.default_profile_pic),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50))
                )
            }

            //search and add reminder
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //search bar
                /*TextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text("Search...", fontSize = 10.sp) },
                    textStyle = TextStyle(fontSize = 10.sp),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(40.dp))
                        .size(45.dp)
                )*/
                Spacer(modifier = Modifier.width(30.dp))
                Button(modifier = Modifier.height (45.dp)
                    .padding(bottom = 10.dp), onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                    Text("+ Add Reminder", color = Color.Black)
                }
            }
        }

    }
    if (showDialog) {
        ReminderDialog(onDismiss = { showDialog = false })
    }
}

class ReminderViewModelFactory(private val reminderRepository: ReminderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(reminderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun BillScreen(reminderViewModel: ReminderViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    var selectedBill by remember { mutableStateOf<Reminder?>(null) }


    val uiState by reminderViewModel.uiState.collectAsState()

    val upcomingReminders = uiState.upcomingReminders
    val completedReminders = uiState.completedReminders
    val lateReminders = uiState.lateReminders

    val upcomingRemindersCount = upcomingReminders.size
    val completedRemindersCount = completedReminders.size
    val lateRemindersCount = lateReminders.size


    Surface(modifier = Modifier
        .padding(horizontal = 30.dp)
        ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Upcoming:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(
                count = upcomingRemindersCount,
                key = { index -> upcomingReminders[index].id },
                itemContent = { index ->
                    val reminder = upcomingReminders[index]
                    ReminderItem(reminder = reminder) {
                        selectedBill = it
                    }
                }
            )

            item {
                Divider(color = Color.Black, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 5.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Completed:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(
                count = completedRemindersCount,
                key = { index -> completedReminders[index].id },
                itemContent = { index ->
                    val reminder = completedReminders[index]
                    ReminderItem(reminder = reminder) {
                        selectedBill = it
                    }
                }
            )

            item {
                Divider(color = Color.Black, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 5.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Late:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(
                count = lateRemindersCount,
                key = { index -> lateReminders[index].id },
                itemContent = { index ->
                    val reminder = lateReminders[index]
                    ReminderItem(reminder = reminder) {
                        selectedBill = it
                    }
                }
            )

            item {
                Divider(color = Color.Black, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 5.dp))
            }
        }
    }

    selectedBill?.let {
        BillDetailsDialog(reminder = it, onDismiss = { selectedBill = null })
    }
}


@Composable
fun ReminderItem(reminder: Reminder, onClick: (Reminder) -> Unit) {

    val color = when (reminder.status) {
        "Upcoming" -> Color(118, 180, 255)
        "Completed" -> Color(126, 217, 87)
        else -> Color(255, 172, 177)
    }

    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(reminder) }
    ) {
        Column(
            modifier = Modifier
                .background(color = color)
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    reminder.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Start
                )
                Text(
                    "RM${reminder.amount}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.ExtraBold
                )

            }
            Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp))
            if (reminder.status == "Completed") {
                Text("Next Payment:", fontSize = 14.sp)
            } else {
                Text("Due:", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                Text(reminder.date, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                Row {
                    if (reminder.status == "Completed") {
                        Checkbox(checked = true, onCheckedChange = null)
                    } else {
                        Checkbox(checked = false, onCheckedChange = null)
                    }
                    Text(text = "Done", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold,)
                }


            }

        }

    }
}


