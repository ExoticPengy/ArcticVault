package com.example.arcticvault.ui.theme

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import com.example.arcticvault.R
import java.util.Calendar

@Composable
fun ReminderDialog(onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }


    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = Color(199, 234, 255),
            shape = RoundedCornerShape(12.dp),
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
                    "New Reminder", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Title: ")
                    TextField(value = title, onValueChange = { title = it },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(40.dp))
                            .size(45.dp)
                            .background(color = Color(231, 245, 255)))
                }


                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Desc: ")
                    TextField(value = desc, onValueChange = { desc = it },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .size(60.dp))

                }
                Spacer(modifier = Modifier.height(16.dp))

                // Amount
                Row {
                    Text(text = "Amount: ")
                    TextField(value = amount, onValueChange = { amount = it },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(40.dp))
                            .size(45.dp))

                }
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Date: ")
                    DatePickerField(value = date, onDateSelected = { newDate -> date = newDate })

                }
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Repeat: ")
                    DropDownMenu()
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Set Category")

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(color = Color(231, 245, 255))
                    .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                    CategoryButton("Utility", selectedCategory, onCategorySelected = { selectedCategory = it })
                    CategoryButton("Payroll", selectedCategory, onCategorySelected = { selectedCategory = it })
                    CategoryButton("Taxes", selectedCategory, onCategorySelected = { selectedCategory = it })
                    /*Button( onClick = {  },
                        colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                        Text("Utility", color = Color.Black)
                    }
                    Button( onClick = {  },
                        colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                        Text("Payroll", color = Color.Black)
                    }
                    Button( onClick = {  },
                        colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                        Text("Taxes", color = Color.Black)
                    }*/
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("+ ADD")
                }
            }
        }
    }
}

@Composable
fun CategoryButton(category: String, selectedCategory: String, onCategorySelected: (String) -> Unit) {
    Button(
        onClick = { onCategorySelected(category) },
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (category == selectedCategory) Color(118, 180, 255)
            else Color(199, 234, 255)
        )
    ) {
        Text(category, color = Color.Black)
    }
}

//repeat frequency option function
@Composable
fun DropDownMenu(modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Once", "Daily", "Monthly", "Yearly")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
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
                })
            }
        }
    }

}


//date picker function
@Composable
fun DatePickerField(modifier: Modifier = Modifier, value: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    fun showDatePicker() {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                onDateSelected("$dayOfMonth/${month + 1}/$year")
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