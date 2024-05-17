package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.arcticvault.R

@Composable
fun BillDetailsDialog(bill: Bill, onDismiss: () -> Unit){
    Dialog(onDismissRequest = onDismiss){
        Surface(
            color = Color(255,255,170),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Row{
                    Text(text = bill.name, fontSize = 20.sp)
                    Column (modifier = Modifier.fillMaxWidth()){
                        Image(painter = painterResource(R.drawable.editicon),
                            contentDescription = "Edit",
                            modifier = Modifier
                                .align(Alignment.End)
                                .size(20.dp))
                    }

                }
                Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp))

                Text(text = bill.amount.toString())
                TextField(value = "Desc...", onValueChange = { },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp))

                Column {
                    Row (modifier = Modifier.padding(10.dp)){
                        Text(text = "Date: ")
                        Text(text = "Repeats: ")
                    }
                    Row (modifier = Modifier.width(120.dp)){
                        DatePickerField(value = bill.dueDate, onDateSelected = { })
                    }
                    Row (modifier = Modifier.width(110.dp)){
                        DropDownMenu()
                    }


                }



                /*OutlinedTextField(
                    value = bill.name,
                    onValueChange = { },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )*/
            }
        }
    }
}