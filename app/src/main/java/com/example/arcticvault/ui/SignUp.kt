package com.example.arcticvault.ui

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arcticvault.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

object SignupDestination {
    val route = "Signup"

}

@Composable
fun SignUp(navController: NavController) {
    var username by remember {mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Function to perform sign up
    val signUp: () -> Unit = {
        if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign-up successful
                        Log.d(TAG, "Sign-up successful")
                        navController.navigate(LoginDestination.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    } else {
                        // Sign-up failed
                        Log.d(TAG, "Sign-up failed: ${task.exception?.message}")
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Sign-up failed: ${task.exception?.message}")
                        }
                    }
                }
        } else {
            // Fields are blank
            Log.d(TAG, "Fields are blank")
            // Handle error or display a message to the user
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
                IconButton(onClick = { navController.navigateUp() }) {
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
                text = "Sign up",
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
                    text = "Sign up",
                    fontSize = 35.sp,
                    color = Color.White
                )
                Text(text = "Sign up to continue", color = Color.White)
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
                        text = "Username",
                        color = Color.White,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 7.dp
                            ) // Add padding to align with TextField
                            .fillMaxWidth(0.8f)
                    )
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        modifier = fieldModifier
                    )

                    Text(
                        text = "Email",
                        color = Color.White,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 7.dp
                            ) // Add padding to align with TextField
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
                            .padding(
                                start = 16.dp,
                                top = 7.dp
                            ) // Add padding to align with TextField
                            .fillMaxWidth(0.8f)
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = fieldModifier
                    )
                }
                Button(onClick = signUp, modifier = Modifier.padding(top = 12.dp)) {
                    Text(text = "Sign Up", color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


}

