package com.example.arcticvault.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun LoginRegisterScreen(navController: NavController){
    Surface {

        Column (modifier = Modifier.fillMaxWidth()
            .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Arctic Vault",
                modifier = Modifier.padding(top = 30.dp)
                    .padding(horizontal = 30.dp),
                fontSize = 35.sp,
                textAlign = TextAlign.Center)
            Text(text = "Finance Managing", modifier = Modifier.padding(top = 60.dp))

            Text(text = "Stay calm and cool")

            Button(onClick = {
                NavController.navigate("login")
            }, modifier = Modifier.padding(top = 120.dp)) {
                Text(text = "Login")
            }
            Button(onClick  = { navController.navigate("signup") }) {
                Text(text = "Sign Up")
            }
        }
    }
}