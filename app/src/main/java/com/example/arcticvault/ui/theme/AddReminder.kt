package com.example.arcticvault.ui.theme

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog

@Composable
fun ReminderDialog(onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = Color(199, 234, 255),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
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

                // Description
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

                // Date
                Row {
                    Text(text = "Date: ")
                    TextField(value = "", onValueChange = {},
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(40.dp))
                            .size(45.dp))

                }
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Repeat: ")
                    DropDownMenu()
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Set Category")

                // Categories
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(color = Color(231,245,255))
                    .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                    Button( onClick = {  },
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
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Add button
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
fun DropDownMenu() {

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