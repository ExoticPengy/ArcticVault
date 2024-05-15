package com.example.arcticvault

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.data.Category
import com.example.arcticvault.model.TransactionModel
import com.example.arcticvault.ui.AppViewModelProvider
import com.example.arcticvault.ui.EditTransactionViewModel
import com.example.arcticvault.ui.theme.montserratFontFamily
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object EditTransactionDestination {
    val route = "Edit"
    val transactionIdArg = "transactionId"
    val routeWithArgs = "$route/{$transactionIdArg}"
}

@Composable
fun EditTransaction(
    onButtonClick: () -> Unit,
    addNewExpense: Boolean = false,
    addNewIncome: Boolean = false,
    editTransactionViewModel: EditTransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val editTransactionUiState by editTransactionViewModel.uiState.collectAsState()
    val transaction: TransactionModel = editTransactionUiState.transaction
    val coroutineScope = rememberCoroutineScope()
    //Check if it is a new transaction: expense/income
    editTransactionViewModel.checkType(transaction, addNewExpense, addNewIncome)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            //Box for top banner
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.height(280.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.topbanner),
                    contentDescription = stringResource(R.string.blue_topbanner_desc),
                    modifier = Modifier
                        .wrapContentHeight(unbounded = true, align = Alignment.Bottom)
                        .height(330.dp)
                        .fillMaxWidth()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .width(320.dp)
                            .padding(top = 40.dp)
                    ) {
                        //Back button
                        Image(
                            painter = painterResource(R.drawable.backbutton),
                            contentDescription = stringResource(R.string.back_button_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    onButtonClick()
                                }
                        )
                        //Title
                        Text(
                            text = "Edit Transaction",
                            fontFamily = montserratFontFamily,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                        //Confirm Button
                        Image(
                            painter = painterResource(R.drawable.confirmicon),
                            contentDescription = stringResource(R.string.confirm_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    if(editTransactionViewModel.validateInput(editTransactionUiState)) {
                                        coroutineScope.launch {
                                            editTransactionViewModel.saveTransaction()
                                        }
                                        onButtonClick()
                                    }
                                    else {
                                        Toast.makeText(context, "Please fill in all fields and pick a category!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        )
                    }

                    //Transaction icon & type
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(transaction.icon),
                            contentDescription = stringResource(editTransactionViewModel.updateIconDesc(transaction.icon)),
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    if (transaction.icon == R.drawable.income) {
                                        editTransactionViewModel.updateUiState(
                                            transaction.copy(
                                                icon = R.drawable.expense,
                                                type = R.string.expense
                                            )
                                        )
                                    } else {
                                        editTransactionViewModel.updateUiState(
                                            transaction.copy(
                                                icon = R.drawable.income,
                                                type = R.string.income
                                            )
                                        )
                                    }
                                }
                        )
                        Text(
                            text = stringResource(transaction.type),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                    //Transaction title text field
                    TextField(
                        value = transaction.title,
                        onValueChange = { editTransactionViewModel.updateUiState(transaction.copy(title = it)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.transaction_screen_title),
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.editicon),
                                contentDescription = stringResource(R.string.edit_desc),
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.Transparent
                            ),
                        modifier = Modifier
                            .widthIn(1.dp, 300.dp)
                    )

                    //Transaction amount text field
                    TextField(
                        value = editTransactionViewModel.formatAmount(transaction.amount),
                        onValueChange = { editTransactionViewModel.updateUiState(transaction.copy(amount = editTransactionViewModel.updateAmount(it))) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.Transparent
                            ),
                        modifier = Modifier
                            .widthIn(1.dp, 280.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //Calender button
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.calendaricon),
                            contentDescription = stringResource(R.string.calendar_desc),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    editTransactionViewModel.showDatePicker = true
                                }
                        )
                    }
                    //Date Picker
                    if (editTransactionViewModel.showDatePicker) {
                        DatePickerDialog(
                            onDateSelected = { editTransactionViewModel.updateUiState(transaction.copy(date = it)) },
                            onDismiss = { editTransactionViewModel.showDatePicker = false },
                            dismissButtonText = R.string.cancel
                        )
                    }
                    Text(
                        text = transaction.date,
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }

                //Time Picker
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TimePicker(transaction = transaction)
                }

                //Category
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.categoryicon),
                        contentDescription = stringResource(R.string.category_desc),
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = stringResource(R.string.set_category),
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                //Category Items
                Box {
                    Image(
                        painter = painterResource(R.drawable.bluecard),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = stringResource(R.string.blue_desc),
                        modifier = Modifier.size(300.dp, 60.dp)
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .size(240.dp, 60.dp)
                        ) {
                            items(editTransactionUiState.categoryList) { category ->
                                Spacer(Modifier.width(20.dp))
                                Box {
                                    DisplayCategory(
                                        category = category,
                                        categoryClick = {
                                            editTransactionViewModel.category = category
                                            editTransactionViewModel.showCategory = true
                                        })
                                    if (transaction.categoryId == category.id) {
                                        Image(
                                            painter = painterResource(R.drawable.confirmicon),
                                            contentDescription = stringResource(R.string.confirm_desc),
                                            alpha = 0.5f,
                                            modifier = Modifier
                                                .size(30.dp)
                                                .align(Alignment.Center)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.width(10.dp))
                        Image(
                            painter = painterResource(R.drawable.addbutton),
                            contentDescription = stringResource(R.string.add_desc),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    editTransactionViewModel.showCreateCategory = true
                                }
                        )
                    }
                }

                if (editTransactionViewModel.showCategory) {
                    CategoryDialog(
                        category = editTransactionViewModel.category,
                        onDismissRequest = {
                            editTransactionViewModel.showCategory = false
                        },
                        onDelete = {
                            if (editTransactionViewModel.category.inUse == 0) {
                                coroutineScope.launch() {
                                    editTransactionViewModel.deleteCategory(editTransactionViewModel.category)
                                }
                                Toast.makeText(context, "Deleted Successfully, Please Refresh.", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(context, "This category is in use by another transaction!", Toast.LENGTH_SHORT).show()
                            }
                            editTransactionViewModel.updateUiState(transaction)
                        },
                        onSelect = {
                            editTransactionViewModel.setCategoryInUse(transaction.categoryId)
                            editTransactionViewModel.updateUiState(transaction.copy(categoryId = editTransactionViewModel.category.id))
                        }
                    )
                }

                if (editTransactionViewModel.showCreateCategory) {
                    editTransactionViewModel.resetCategory()
                    CreateCategoryDialog(
                        editTransactionViewModel = editTransactionViewModel,
                        coroutineScope = coroutineScope,
                        onDismissRequest = {
                            editTransactionViewModel.showCreateCategory = false
                            },
                        context = context,
                        transaction = transaction
                    )
                }
                
                //Description Box
                Box {
                    Image(
                        painter = painterResource(R.drawable.bluecard),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = stringResource(R.string.blue_desc),
                        modifier = Modifier
                            .size(300.dp, 60.dp)
                    )
                    TextField(
                        value = transaction.description,
                        onValueChange = { editTransactionViewModel.updateUiState(transaction.copy(description = it)) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.description),
                                textAlign = TextAlign.Center,
                                fontFamily = montserratFontFamily,
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = montserratFontFamily
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults
                            .colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.Transparent
                            ),
                        modifier = Modifier
                            .width(280.dp)
                            .height(60.dp)
                            .align(Alignment.TopCenter)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(100.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    //Delete button
                    Image(
                        painter = painterResource(R.drawable.trashicon),
                        contentDescription = stringResource(R.string.trash_desc),
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                coroutineScope.launch {
                                    editTransactionViewModel.deleteTransaction(editTransactionUiState)
                                }
                                onButtonClick()
                            }
                    )

                    //Refresh button
                    Image(
                        painter = painterResource(R.drawable.refreshbutton),
                        contentDescription = stringResource(R.string.refresh_desc),
                        modifier = Modifier
                            .size(35.dp)
                            .clickable {
                                coroutineScope.launch {
                                    editTransactionViewModel.updateUiState(transaction)
                                }
                            }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    transaction: TransactionModel,
    editTransactionViewModel: EditTransactionViewModel = viewModel()
) {
    val timePickerState = rememberTimePickerState(is24Hour = true)

    Image(
        painter = painterResource(R.drawable.clockicon),
        contentDescription = stringResource(R.string.clock_desc),
        modifier = Modifier
            .size(30.dp)
            .clickable {
                editTransactionViewModel.showTimePicker = true
            }
    )
    Text(
        text = transaction.time,
        textAlign = TextAlign.Center,
        fontFamily = montserratFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 20.dp)
    )

    if (editTransactionViewModel.showTimePicker) {
        TimePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    onClick = {
                        editTransactionViewModel.updateUiState(
                            transaction.copy(
                                time = timePickerState.hour.toString() + ":" + if(timePickerState.minute == 0){"00"} else timePickerState.minute.toString()
                            ))
                        editTransactionViewModel.showTimePicker = false
                    }
                ) { Text(
                    text = stringResource(R.string.ok),
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        editTransactionViewModel.showTimePicker = false
                    }
                ) { Text(
                    text = stringResource(R.string.cancel),
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = stringResource(R.string.select_time),
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)

        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton()
                    confirmButton()
                }
            }
        }
    }
}

@Composable
fun DisplayCategory(category: Category, categoryClick: () -> Unit) {
    Text(
        text = category.title,
        textAlign = TextAlign.Center,
        fontFamily = montserratFontFamily,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier
            .background(color = Color(category.color.toULong()), shape = RoundedCornerShape(50))
            .padding(5.dp)
            .clickable { categoryClick() }
    )
}

@Composable
fun CreateCategoryDialog(
    editTransactionViewModel: EditTransactionViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
    transaction: TransactionModel,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.add_new_category),
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = editTransactionViewModel.categoryTitle,
                    onValueChange = { editTransactionViewModel.categoryTitle = it },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.category),
                            textAlign = TextAlign.Start,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = montserratFontFamily
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults
                        .colors(

                        ),
                    modifier = Modifier
                        .width(280.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(color = Color(editTransactionViewModel.colorPicked.toULong()))
                            .border(width = 2.dp, color = Color.Black)
                    )
                    TextButton(
                        onClick = {
                            editTransactionViewModel.showColorPicker = true
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.pick_color),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                }

                if (editTransactionViewModel.showColorPicker) {
                    ColorPicker(
                        editTransactionViewModel = editTransactionViewModel,
                        onDismiss = { editTransactionViewModel.showColorPicker = false }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            editTransactionViewModel.resetCategory()
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    TextButton(
                        onClick = {
                            if(editTransactionViewModel.validateCategory(Category(title = editTransactionViewModel.categoryTitle, color = editTransactionViewModel.colorPicked, inUse = 0))) {
                                coroutineScope.launch {
                                    editTransactionViewModel.addCategory(Category(title = editTransactionViewModel.categoryTitle, color = editTransactionViewModel.colorPicked, inUse = 0))
                                }
                                Toast.makeText(context, "Created Successfully, Please Refresh.", Toast.LENGTH_SHORT).show()
                                onDismissRequest()
                            }
                            else {
                                Toast.makeText(context, "Please enter a category title!", Toast.LENGTH_SHORT).show()
                            }
                            editTransactionViewModel.updateUiState(transaction)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryDialog(
    category: Category,
    onDismissRequest: () -> Unit,
    onDelete: () -> Unit,
    onSelect: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category.title,
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .background(
                            color = Color(category.color.toULong()),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(5.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            onDelete()
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                    TextButton(
                        onClick = {
                            onSelect()
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.select),
                            textAlign = TextAlign.Center,
                            fontFamily = montserratFontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.width(80.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorPicker(
    editTransactionViewModel: EditTransactionViewModel,
    onDismiss: () -> Unit
) {
    val controller = rememberColorPickerController()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                editTransactionViewModel.colorPicked = it.color.value.toLong()
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
        )
        TextButton(
            onClick = {
                onDismiss()
            }
        ) {
            Text(
                text = stringResource(R.string.ok),
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}


