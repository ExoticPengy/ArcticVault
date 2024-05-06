package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.arcticvault.GreetingMessage
import com.example.arcticvault.R
import com.example.arcticvault.TopBanner

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
    Surface() {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -180.dp),
            contentScale = ContentScale.FillWidth
        )
        Column {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Bills and Payments",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            //search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .offset(y = 90.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search...", fontSize = 10.sp) },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(40.dp))
                        .size(45.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Button(modifier = Modifier.height (45.dp), onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(199, 234, 255))) {
                    Text("+ Add Reminder", color = Color.Black)
                }
            }
        }

    }
}

@Composable
fun BillScreen() {
    Surface (modifier = Modifier
        .offset(y = -140.dp)
        .padding(30.dp)){
        Column{
            Spacer(modifier = Modifier.height(16.dp))

            Text("Upcoming:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            BillItem(Bill("Payroll", 10000.00, "1 March 2024", Color(118,180,255),"Upcoming"))

            Text("Completed:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            BillItem(Bill("Electricity Bills", 150.00, "1 March 2024", Color(126,217,87),"Done"))


            Text("Late:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        }
    }

}

data class Bill(
    val name: String,
    val amount: Double,
    val dueDate: String,
    val color: Color,
    val status: String
)

@Composable
fun BillItem(bill: Bill) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = bill.color)
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
            Text("Due:", fontSize = 14.sp)

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
