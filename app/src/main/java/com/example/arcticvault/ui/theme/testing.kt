package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcticvault.R
@Composable
fun ABC(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.milestones),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
        )
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        Column {
            Text(
                text = "ABC\n" +
                        "1 of ABC\n" +
                        "ABC",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 80.dp)
            )
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ){
        Column {
            Text(
                text = "08/05/2023",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 55.dp)
            )
        }
    }
}