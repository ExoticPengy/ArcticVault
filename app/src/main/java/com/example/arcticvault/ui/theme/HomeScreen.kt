package com.example.arcticvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier, name:String) {
    Surface (
        Modifier.fillMaxSize(),
        color = Color(231,245,255)){

    }
    Column {
        TopBanner()
        GreetingMessage(modifier = Modifier,name = name)
        HomeScreenUI()
        UpcomingBills()
    }

}

@Composable
fun TopBanner(){
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = Color(0,0,0)
    ){
        Row (modifier = Modifier.padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center){
            Text(text = "Arctic Vault", color = Color.White, fontSize = 25.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun GreetingMessage(modifier: Modifier = Modifier, name:String){
    Surface(modifier = Modifier
        .padding(top = 10.dp, bottom = 10.dp)
        .fillMaxWidth(),
        color = Color(231,245,255)
    ) {
        Row(modifier = modifier.fillMaxWidth())
        {
            Image(painter = painterResource(R.drawable.profilepic), contentDescription = "Profile Picture")

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    text = "Welcome ",
                    fontSize = 40.sp
                )
                Text(
                    modifier = Modifier
                        .padding(),
                    text = " $name",
                    fontSize = 40.sp
                )
            }

        }
    }
}

@Composable
fun HomeScreenUI() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color(244, 245, 255)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
        ) {
            Text(
                text = "Features",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FeatureItem(R.drawable.trans_icon, "Transactions")
                FeatureItem(R.drawable.budget_icon, "Budgeting")
                FeatureItem(R.drawable.bill_icon, "Bills & Payments")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FeatureItem(R.drawable.goal_icon, "Financial Goals")
                FeatureItem(R.drawable.t_analysis_icon, "Transactions Analysis", isLonger = true)
                FeatureItem(R.drawable.loan_icon, "Loan and Debt Management", isLonger = true)
            }
        }
    }
}

@Composable
fun FeatureItem(icon: Int, text: String, isLonger: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(
                    110.dp,
                    height = if (isLonger) 140.dp else 120.dp
                )
                .background(Color.White)
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 90.dp)
            )
        }
    }
}

@Composable
fun UpcomingBills(){
    Surface (modifier = Modifier.padding(20.dp), color = Color(231,245,255)){
        Row {
            Text(text = "Reminders",
                fontSize = 20.sp)
        }
    }
}
