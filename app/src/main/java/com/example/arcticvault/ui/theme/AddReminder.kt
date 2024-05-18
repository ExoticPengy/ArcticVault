package com.example.arcticvault.ui.theme

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.model.ReminderEntryModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object AddReminderDestination {
    val route = "AddReminder"
    val reminderIdArg = "reminderId"
    val routeWithArgs = "$route/{$reminderIdArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDialog(
    onDismiss: () -> Unit,
    reminderEntryViewModel: ReminderEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val reminderEntryUiState by reminderEntryViewModel.uiState.collectAsState()
    val reminder: ReminderEntryModel = reminderEntryUiState.reminder
    val coroutineScope = rememberCoroutineScope()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = Color(199, 234, 255),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier.padding(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(R.drawable.closeicon),
                    contentDescription = "Close",
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(40.dp)
                        .clickable(onClick = onDismiss))
            }
            Column (modifier = Modifier.padding(10.dp)){

                Text(
                    "New Reminder", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.padding(vertical = 10.dp))

                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Title: ", fontWeight = FontWeight.ExtraBold)
                    TextField(value = reminder.title,
                        onValueChange = { reminderEntryViewModel.updateUiState(reminder.copy(title = it)) },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .size(45.dp)
                            .background(color = Color(231, 245, 255)),
                        textStyle = TextStyle(fontSize = 12.sp)
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Desc: ", fontWeight = FontWeight.ExtraBold)
                    TextField(value = reminder.desc,
                        onValueChange = { reminderEntryViewModel.updateUiState(reminder.copy(desc = it)) },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .size(60.dp),
                        textStyle = TextStyle(fontSize = 12.sp))


                }
                Spacer(modifier = Modifier.height(16.dp))

                // Amount
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Amount: ", fontWeight = FontWeight.ExtraBold)
                    TextField(value = reminder.amount.toString(),
                        onValueChange = { reminderEntryViewModel.updateUiState(reminder.copy(amount = it.toDouble())) },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .size(45.dp),
                        textStyle = TextStyle(fontSize = 12.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Date: ", fontWeight = FontWeight.ExtraBold)

                    DatePickerField(
                        value = reminder.date,
                        onDateSelected = { newDate ->
                            reminderEntryViewModel.updateUiState(reminder.copy(date = newDate))
                        },
                        reminderEntryViewModel = reminderEntryViewModel
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Repeat: ", fontWeight = FontWeight.ExtraBold)
                    DropDownMenu(
                        suggestions = listOf("Once", "Weekly", "Monthly", "Yearly"),
                        onItemSelected = { selectedValue ->
                            reminderEntryViewModel.updateUiState(reminder.copy(repeat = selectedValue))
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val validationMessage = reminderEntryViewModel.saveReminder()
                            if (validationMessage.isEmpty()) {
                                onDismiss()
                            } else {
                                errorMessage = validationMessage
                                showErrorDialog = true
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("+ ADD")
                }

                if (showErrorDialog) {
                    ErrorDialog(message = errorMessage) {
                        showErrorDialog = false
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorDialog(message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Error", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = message, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        }
    }
}



//repeat frequency option function
@Composable
fun DropDownMenu(
    suggestions: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Once", "Weekly", "Monthly", "Yearly")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {  },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                }
            ,
            label = {Text("Option")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->

                DropdownMenuItem(text = { Text(text = label) }, onClick = {
                    selectedText = label
                    expanded = false
                    onItemSelected(label)
                })
            }
        }
    }

}


//date picker function
@Composable
fun DatePickerField(modifier: Modifier = Modifier, value: String, onDateSelected: (String) -> Unit,
                    reminderEntryViewModel: ReminderEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    fun showDatePicker() {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val newDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(newDate)
                if (isDateBeforeToday(newDate)) {
                    reminderEntryViewModel.updateUiState(reminderEntryViewModel.uiState.value.reminder.copy(status = "Late"))
                }
                else
                    reminderEntryViewModel.updateUiState(reminderEntryViewModel.uiState.value.reminder.copy(status = "Upcoming"))

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    OutlinedTextField(
        value = value,
        onValueChange = { },
        readOnly = true,
        modifier = Modifier.clickable { showDatePicker() },
        label = { Text("DD/MM/YYYY") },
        trailingIcon = {
            Image(
                painter = painterResource(R.drawable.calendaricon),
                contentDescription = "Select Date",
                modifier = Modifier
                    .clickable { showDatePicker() }
                    .size(30.dp)
            )
        }
    )
}

fun isDateBeforeToday(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val selectedDate = dateFormat.parse(dateString)
    val today = Calendar.getInstance().time
    return selectedDate?.before(today) == true
}