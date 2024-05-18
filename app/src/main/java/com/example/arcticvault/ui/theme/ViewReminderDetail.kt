package com.example.arcticvault.ui.theme

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Reminder


@Composable
fun BillDetailsDialog(reminder: Reminder,
                      onDismiss: () -> Unit){

    var selectedBill by remember { mutableStateOf<Boolean?>(null) }
    val reminderViewModel: ReminderViewModel = viewModel(factory = AppViewModelProvider.Factory)

    Dialog(onDismissRequest = onDismiss){
        Surface(
            color = Color(184, 245, 226),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Row{
                    Text(text = reminder.title, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    Column (modifier = Modifier.fillMaxWidth()){
                        Image(painter = painterResource(R.drawable.editicon),

                            contentDescription = "Edit",
                            modifier = Modifier
                                .clickable {
                                    selectedBill = true
                                }
                                .align(Alignment.End)
                                .size(20.dp))
                    }

                }
                Divider(color = Color.Black, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 5.dp))

                Text(text = reminder.amount.toString(), fontWeight = FontWeight.ExtraBold)
                TextField(value = reminder.desc, onValueChange = { },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        DatePickerField(value = reminder.date, onDateSelected = {  })
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Row {
                        Text(text = "Repeats:")
                        Text(text = reminder.repeat)
                    }

                }
                Row(modifier = Modifier.padding(top = 10.dp),
                    ) {
                    Text(text = "Status :")
                    Text(text = reminder.status)
                }


                Row(modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 10.dp)) {
                    Button(onClick = {  }) {
                        Text("View Related Transactions")
                    }
                    Spacer(modifier = Modifier.weight(1f))

                }
            }
        }

        selectedBill?.let {
            EditScreenDialog(
                reminder = reminder,
                onDismiss = onDismiss,
                onSave = {
                    reminderViewModel.updateReminder(it)
                    onDismiss()
                }
            ) {

            }
        }
    }
}

