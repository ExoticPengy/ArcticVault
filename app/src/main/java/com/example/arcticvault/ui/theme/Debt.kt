package com.example.arcticvault.ui.theme

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.arcticvault.R
import java.util.Calendar

object DebtDestination {
    val route = "debt"
}
@Composable
fun Debt() {
    var isDrawerOpen by remember { mutableStateOf(false) }
    var categoryList by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            Text(
                text = "Debt",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 30.dp),
                fontSize = 35.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Manage all your debts in Arctic Vault",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 60.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Balance by Category",
                modifier = Modifier.padding(top = 150.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Debt", modifier = Modifier.padding(start = 16.dp))
                // Add button to open drawer
                Button(
                    onClick = { isDrawerOpen = true },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text("Add+")
                }
            }

            // Search bar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Replace this with your search bar implementation
                TextField(
                    value = "",
                    onValueChange = { /* Handle search query change */ },
                    placeholder = { Text("Search") },
                    modifier = Modifier
                        .height(48.dp) // Adjusts the height of the search bar
                        .padding(horizontal = 16.dp)
                        .weight(1f)
                        .size(20.dp),
                    shape = RoundedCornerShape(16.dp) // Rounds the corners of the search bar
                )
            }
        }
    }

    if (isDrawerOpen) {
        Drawer(
            onClose = { isDrawerOpen = false }
        )
    }
}

@Composable
fun DropDownMenu(modifier: Modifier = Modifier,
                 onOptionSelected: (String) -> Unit,
                 options: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption  by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        OutlinedTextField(
            value = selectedOption ,
            onValueChange = { },
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
            options.forEach { label ->

                DropdownMenuItem(text = { Text(text = label) }, onClick = {
                    selectedOption = label
                    onOptionSelected(label)
                    expanded = false
                })
            }
        }
    }

}


@Composable
fun Drawer(onClose: () -> Unit) {
    val categories = listOf("Car Loan", "Credit Card", "Mortgage", "Others")
    var selectedCategory by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var currentBalance by remember { mutableStateOf("") }
    var annualRate by remember { mutableStateOf("") }
    var minPayment by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)) // Overlay background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Category")

            Spacer(modifier = Modifier.height(8.dp))

            DropDownMenu(
                onOptionSelected = { selectedCategory = it },
                options = categories
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Nickname ")
            TextField(value = nickname, onValueChange = { nickname = it})

            Text(text = "Current Balance")
            TextField(value = currentBalance, onValueChange = { currentBalance = it})

            Text(text = "Annual Rate")
            TextField(value = annualRate, onValueChange = { annualRate = it})

            Text(text = "Minimum Payment")
            TextField(value = minPayment, onValueChange = { minPayment = it})

            Text(text = "Date: ")
            DatePickerField(value = date, onDateSelected = { newDate -> date = newDate })


            // Close button
            Button(
                onClick = onClose,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Close")
            }
        }
    }
}

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
