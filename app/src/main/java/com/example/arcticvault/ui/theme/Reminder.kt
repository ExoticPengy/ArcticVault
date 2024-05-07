package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.arcticvault.R

@Composable
fun ReminderScreen() {
    Surface {

    }
    Column {
        ReminderTopUi()
        BillScreen()
    }
}

@Composable
fun ReminderTopUi(){
    var search by remember { mutableStateOf("") }

    var showDialog by remember {
        mutableStateOf(false)
    }

    Surface() {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Bills and Payments",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(horizontal = 100.dp)
                )
            }

            //search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text("Search...", fontSize = 10.sp) },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(40.dp))
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Button(modifier = Modifier.height (45.dp), onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                    Text("+ Add Reminder", color = Color.Black)
                }
            }
        }

    }
    if (showDialog) {
        ReminderDialog(onDismiss = { showDialog = false })
    }
}

@Composable
fun BillScreen() {
    var selectedBill by remember { mutableStateOf<Bill?>(null) }

    Surface (modifier = Modifier
        .padding(horizontal = 30.dp)
        .padding(top = 10.dp)){
        Column{
            Spacer(modifier = Modifier.height(16.dp))
            Text("Upcoming:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            BillItem(Bill("Payroll", 10000.00, "1 March 2024","Upcoming")){
                selectedBill = it
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Completed:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            BillItem(Bill("Electricity Bills", 150.00, "1 March 2024","Done")){
                selectedBill = it
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Late:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            BillItem(Bill("House Rent", 1050.00, "1 March 2024","Late")){
                selectedBill = it
            }

        }
    }

    selectedBill?.let {
        BillDetailsDialog(bill = it, onDismiss = { selectedBill = null })
    }

}

data class Bill(
    val name: String,
    val amount: Double,
    val dueDate: String,
    val status: String
)

@Composable
fun BillItem(bill: Bill, onClick: (Bill) -> Unit) {

    val color = when (bill.status) {
        "Upcoming" -> Color(118, 180, 255)
        "Done" -> Color(126, 217, 87)
        else -> Color(255, 172, 177)
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(bill) }
    ) {
        Column(
            modifier = Modifier
                .background(color = color)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    bill.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    "RM${bill.amount}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.End
                )
            }
            if (bill.status == "Done") {
                Text("Next Payment:", fontSize = 14.sp)
            } else {
                Text("Due:", fontSize = 14.sp)
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                Text(bill.dueDate, fontSize = 14.sp)
                if (bill.status == "Done") {
                    Checkbox(checked = true, onCheckedChange = null)
                } else {
                    Checkbox(checked = false, onCheckedChange = null)
                }
            }

        }
    }
}


