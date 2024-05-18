package com.example.arcticvault.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Reminder
import kotlinx.coroutines.launch

@Composable
fun EditScreenDialog(reminderEntryViewModel: ReminderEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
                     reminder: Reminder,
                     onDismiss: () -> Unit,
                     onSave: (Reminder) -> Unit,
                     onDelete: () -> Unit){

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var title by remember { mutableStateOf(reminder.title) }
    var amount by remember { mutableStateOf(reminder.amount.toString()) }
    var date by remember { mutableStateOf(reminder.date) }
    var desc by remember { mutableStateOf(reminder.desc) }
    var status by remember { mutableStateOf(reminder.status) }
    var deleteConfimation by remember { mutableStateOf(false) }
    var completed by remember { mutableStateOf(reminder.status == "Completed") }


    Dialog(onDismissRequest = onDismiss){
        Surface(
            color = Color(157, 228, 245),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ){
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(R.drawable.closeicon),
                    contentDescription = "Close",
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(40.dp)
                        .clickable(onClick = onDismiss))
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(text = "Edit Reminder", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Divider(color = Color.Black, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 5.dp))
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = desc, onValueChange = { desc = it }, label = { Text("Description") })
                Spacer(modifier = Modifier.height(8.dp))


                TextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
                Spacer(modifier = Modifier.height(8.dp))


                Row {
                    Surface(
                        color = Color(220, 227, 233),
                    ) {
                        DatePickerField(
                            modifier = Modifier.fillMaxWidth(),
                            value = date,
                            onDateSelected = { selectedDate ->
                                date = selectedDate
                                status = if (isDateBeforeToday(selectedDate)) {
                                    "Late"
                                } else {
                                    "Upcoming"
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row (verticalAlignment = Alignment.CenterVertically){
                    Checkbox(
                        checked = completed,
                        onCheckedChange = { isChecked -> completed = isChecked
                        status = if (isChecked){
                            "Completed"
                        }else{
                            if (isDateBeforeToday(date)) {
                                "Late"
                            } else {
                                "Upcoming"
                            }
                        }}
                    )
                    Text(text = "Done")
                }


                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Button(modifier = Modifier.padding(start = 10.dp),colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = { deleteConfimation = true }) {
                        Text("Delete")
                    }

                    Spacer(modifier = Modifier.width(5.dp))



                    Button( onClick = {
                        val updatedReminder = reminder.copy(
                            title = title,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            date = date,
                            desc = desc,
                            status = status
                        )
                        val validationMessage = validateReminder(updatedReminder)
                        if (validationMessage.isEmpty()) {
                            onSave(updatedReminder)
                            onDismiss()
                        } else {
                            errorMessage = validationMessage
                            showErrorDialog = true
                        }
                    }, modifier = Modifier.padding(start = 70.dp)) {
                        Text("Save")
                    }

                    Spacer(modifier = Modifier.width(4.dp))


                }
            }
        }
    }


    if (deleteConfimation) {
        DeleteConfirmationBox(
            reminder = reminder,
            onConfirm = {
                onDelete()
                deleteConfimation = false
                onDismiss()
            },
            onDismiss = { deleteConfimation = false },
            onEditScreenDismiss = onDismiss
        )
    }

    if (showErrorDialog) {
        ErrorDialog(message = errorMessage) {
            showErrorDialog = false
        }
    }
}

fun validateReminder(reminder: Reminder): String {
    return when {
        reminder.title.isBlank() -> "Title is required"
        reminder.desc.isBlank() -> "Description is required"
        reminder.amount <= 0 -> "Amount must be greater than zero"
        reminder.date.isBlank() -> "Date is required"
        reminder.repeat.isBlank() -> "Repeat frequency is required"
        else -> ""
    }
}

@Composable
fun DeleteConfirmationBox(reminder: Reminder,
                          reminderEntryViewModel: ReminderEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
                          onConfirm: () -> Unit, onDismiss: () -> Unit, onEditScreenDismiss: () -> Unit){
    val coroutineScope = rememberCoroutineScope()
    val reminderUiState by reminderEntryViewModel.uiState.collectAsState()


    Dialog(onDismissRequest = onDismiss){
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Confirm Deletion", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Are you sure you want to delete this reminder?")
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = { onConfirm()
                        coroutineScope.launch {
                            reminderEntryViewModel.deleteReminder(reminder)
                        }
                        onDismiss()
                        onEditScreenDismiss()
                }) {

                        Text("Yes")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onDismiss) {
                        Text("No")
                    }
                }
            }
        }
    }
}