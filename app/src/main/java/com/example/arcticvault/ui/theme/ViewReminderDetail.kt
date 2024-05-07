package com.example.arcticvault.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun BillDetailsDialog(bill: Bill, onDismiss: () -> Unit){
    Dialog(onDismissRequest = onDismiss){
        Surface(
            color = Color(118, 180, 255),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ){
            Column(modifier = Modifier.padding(16.dp)
                .fillMaxWidth()) {
                Text(text = bill.name)
                Text(text = bill.amount.toString())

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