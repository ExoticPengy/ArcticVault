package com.example.arcticvault.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.arcticvault.R
import com.google.firebase.auth.FirebaseAuth


object StartPageDestination {
    val route = "Start"

}

@Composable
fun LoginRegisterScreen(navController: NavHostController){
    val firebaseAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    Surface {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.loginregisterbackground),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize()
            )
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Arctic Vault",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 60.dp)
                        .padding(horizontal = 30.dp),
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Finance Managing",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 130.dp),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Stay calm and cool",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { navController.navigate(LoginDestination.route) },
                    modifier = Modifier.padding(top = 150.dp)
                ) {
                    Text(text = "Login")
                }
                Button(
                    onClick = { navController.navigate(SignupDestination.route) },
                    modifier = Modifier.padding(top = 16.dp)
                ){
                    Text(text = "Sign Up")
                }
            }
        }
    }
}

