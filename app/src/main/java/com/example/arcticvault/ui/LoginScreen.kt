package com.example.arcticvault.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.arcticvault.R

object LoginDestination {
    val route = "login"

}
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Handle back button press to navigate to LoginRegisterScreen
    BackHandler {
        navController.navigate(StartPageDestination.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    Surface {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.background1),
            contentDescription = "background"
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 10.dp)
            ) {
                // Adding back button functionality
                IconButton(onClick = {
                    navController.navigate(StartPageDestination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }) {
                    Image(
                        painter = painterResource(R.drawable.backbutton2),
                        contentDescription = "Back Button",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "Back",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 5.dp)
                )
            }
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 35.sp,
                    color = Color.White
                )
                Text(text = "Sign in to continue", color = Color.White)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val fieldModifier = Modifier
                        .fillMaxWidth(0.8f)  // Shorter length
                        .background(Color(0xFF4B4E5C), shape = RoundedCornerShape(20.dp))

                    Text(
                        text = "Email",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 7.dp) // Add padding to align with TextField
                            .fillMaxWidth(0.8f)
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = fieldModifier
                    )
                    Text(
                        text = "Password",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 7.dp) // Add padding to align with TextField
                            .fillMaxWidth(0.8f)
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = fieldModifier
                    )
                }
                Button(onClick = { navController.navigate(HomeDestination.route) }, modifier = Modifier.padding(top = 12.dp)) {
                    Text(text = "Login", color = Color.White)
                }

                Spacer(modifier = Modifier.height(4.dp)) // Shorter space between the buttons
                Button(
                    onClick = { navController.navigate(SignupDestination.route) },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF22263E) // rgb(34, 38, 62)
                    )
                ) {
                    Text(text = "Sign Up", color = Color.White)
                }
            }
        }
    }
}








