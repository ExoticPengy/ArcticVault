package com.example.arcticvault.ui

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.arcticvault.R
import com.example.arcticvault.data.Debt
import com.example.arcticvault.model.DebtInputModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Calendar

object DebtDestination {
    val route = "debt"
}


@Composable
fun DebtScreen(
    navController: NavController,
    debtEntryViewModel: DebtEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var isDrawerOpen by remember { mutableStateOf(false) }
    var isAddMode by remember { mutableStateOf(false) }//use to determine dialog use to add or update
    var selectedDebt by remember { mutableStateOf<Debt?>(null) }
    var categoryList by remember { mutableStateOf("") }
    val debtUIState = debtEntryViewModel.debtUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    Surface {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 20.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.backbutton),
                    contentDescription = "back",
                    modifier = Modifier.size(30.dp)
                        .clickable { navController.navigateUp() }
                )
                Text(
                    text = "Debt",
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .width(IntrinsicSize.Min)
                )
                Image(
                    painter = painterResource(R.drawable.default_profile_pic),
                    contentDescription = "Profile",
                    modifier = Modifier.size(50.dp)
                        .clip(CircleShape)
                        .clickable { /*navController.navigate("AccountSetting") */}
                )
            }
            Text(
                text = "Manage all your debts in",
                modifier = Modifier.padding(top = 1.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Arctic Vault",
                modifier = Modifier.padding(top = 1.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Debt",
                    modifier = Modifier.padding(start = 16.dp)
                        .padding(top = 40.dp)
                )
                // Add button to open drawer
                Button(
                    onClick = {
                        isDrawerOpen = true
                        isAddMode = true
                    },
                    modifier = Modifier.padding(end = 16.dp)
                        .padding(top = 20.dp)
                ) {
                    Text("Add+")
                }
            }

            //Recycle view for debt list
            val debtList by debtEntryViewModel.getListOfDebt().collectAsState(initial = emptyList())
            LazyColumn(content = {
                itemsIndexed(debtList, itemContent = { index, item ->
                    var context = LocalContext.current
                    var debt = item
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {

                                //Pass data to next page
                                val gson = Gson()
                                val debtJson = gson.toJson(debt)
                                val encodedDebtJson =
                                    URLEncoder.encode(debtJson, StandardCharsets.UTF_8.toString())
                                val debtDetailsRoute = DebtDetailsDestination.route
                                navController.navigate("debtDetail/$encodedDebtJson")
                            },
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(debt.nickname, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            //Edit function
                                            isDrawerOpen = true
                                            isAddMode = false
                                            selectedDebt = debt

                                        }
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.editicon),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(25.dp)
                                    )
                                }
                            }



                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    Text("Balance", fontWeight = FontWeight.Bold)
                                    Text("RM${debt.currentBalance}",)
                                }
                                Column {
                                    Text("APR", fontWeight = FontWeight.Bold)
                                    Text("${debt.annualRate}%",)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    Text(
                                        "Minimum Payment", fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "RM${debt.minPayment}",
                                    )
                                }
                                Column {
                                    Text(
                                        "Category", fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        debt.categories,
                                    )
                                }
                            }

                        }
                    }
                })
            })

        }



    }

    if(isDrawerOpen){
        Drawer(
            viewModel = debtEntryViewModel,
            onClose = {
                coroutineScope.launch {
                    if(isAddMode){
                        debtEntryViewModel.saveDebt()
                    } else{
                        debtEntryViewModel.updateDebt(selectedDebt?.id)
                    }

                }
                isDrawerOpen = false
            },
            onCancel = {
                        isDrawerOpen = false
            },
            isAddMode2 = isAddMode,
            debt = selectedDebt
        )
    }

}



@Composable
fun DropDownMenu(initialSelectedOption: String,
                 modifier: Modifier = Modifier,
                 onOptionSelected: (String) -> Unit,
                 options: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption  by remember { mutableStateOf(initialSelectedOption) }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        OutlinedTextField(
            value = selectedOption ,
            readOnly = true,
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
fun Drawer(viewModel: DebtEntryViewModel,
           onClose: () -> Unit,
           onCancel: () -> Unit,
           isAddMode2: Boolean,
           debt: Debt?) {

    //Retrieve value when user select the debt else show null value for user to save data
    val categories = listOf("Car Loan", "Credit Card", "Mortgage", "Others")
    var selectedCategory by remember { mutableStateOf("") }
    var nickname by remember { if(isAddMode2) mutableStateOf("") else mutableStateOf(debt?.nickname.toString()?:"") }
    var currentBalance by remember { if(isAddMode2) mutableStateOf("") else mutableStateOf(debt?.currentBalance.toString()?: "") }
    var annualRate by remember { if(isAddMode2) mutableStateOf("") else mutableStateOf(debt?.annualRate.toString()?: "") }
    var minPayment by remember { if(isAddMode2) mutableStateOf("") else mutableStateOf(debt?.minPayment.toString()?: "") }
    var date by remember { if(isAddMode2) mutableStateOf("") else mutableStateOf(debt?.date.toString()?: "") }


    if(!isAddMode2){
        selectedCategory = debt?.categories ?: ""
    }

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
                initialSelectedOption = selectedCategory,
                onOptionSelected = { selectedCategory = it },
                options = categories,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Nickname ")
            TextField(value = nickname, onValueChange = { newValue -> nickname = newValue})

            Text(text = "Current Balance")
            TextField(value = currentBalance, onValueChange = { currentBalance = it})

            Text(text = "Annual Rate")
            TextField(value = annualRate, onValueChange = { annualRate = it})

            Text(text = "Minimum Payment")
            TextField(value = minPayment, onValueChange = { minPayment = it})

            Text(text = "Date: ")
            DatePickerField(value = date, onDateSelected = { newDate -> date = newDate })

            val context = LocalContext.current
            Button(
                onClick = {
                    onCancel()
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text("Cancel")
            }
            // Close button
            Button(
                onClick = {
                    val validationError = validateInput(
                        selectedCategory,
                        nickname,
                        currentBalance,
                        annualRate,
                        minPayment,
                    )

                    if(validationError == null){
                        viewModel.updateUiState(
                            DebtInputModel(
                                categories = selectedCategory,
                                nickname = nickname,
                                currentBalance = currentBalance.toDouble(),
                                annualRate = annualRate.toInt(),
                                minPayment = minPayment.toDouble(),
                                date = date,
                                paymentFrequency = "Monthly"
                            )
                        )
                        onClose()
                    } else{
                        Toast.makeText(context, "${validationError}", Toast.LENGTH_SHORT).show()
                        Log.e("Validation Error", validationError)
                    }

                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if(isAddMode2) "Save" else "Update")
            }
        }
    }
}


fun validateInput(
    selectedCategory: String,
    nickname: String,
    currentBalance: String,
    annualRate: String,
    minPayment: String,
): String? {
    if (selectedCategory.isBlank()) {
        return "Please select a category"
    }
    if (nickname.isBlank()) {
        return "Nickname cannot be empty"
    }
    if (currentBalance.isBlank()) {
        return "Current balance cannot be empty"
    } else{
        if(currentBalance.toDouble() <= 0){
            return "Current balance must be greater than zero"
        }
    }
    if (annualRate.isBlank()) {
        return "Annual rate cannot be empty"
    } else{
        if(annualRate.toInt() < 0){
            return "Annual rate cannot be negative"
        }
    }
    if (minPayment.isBlank()) {
        return "Minimum payment cannot be empty"
    } else{
        if(currentBalance.toDouble() <= 0){
            return "Minimum payment must be greater than zero"
        }
    }
    return null
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
