package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arcticvault.R

object AccountSettingDestination {
    val route = "AccountSetting"

}

@Composable
fun AccountSetting(navController: NavController) {
    var username by remember { mutableStateOf("") }
    val email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsernameEditing by remember { mutableStateOf(false) }
    var isPasswordEditing by remember { mutableStateOf(false) }
    var isNotificationEnabled by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showProfilePicDialog by remember { mutableStateOf(false) }


    Surface {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.backbutton),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { /*navController.navigate("home")*/ }
                )
                Text(
                    text = "Account Setting",
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .width(IntrinsicSize.Min)
                )
                Image(
                    painter = painterResource(R.drawable.default_profile_pic),
                    contentDescription = "Profile",
                    modifier = Modifier.size(50.dp)
                        .clip(CircleShape)
                )
            }
            Text(
                text = "Profile",
                modifier = Modifier
                    .padding(top = 35.dp)
                    .padding(horizontal = 15.dp),
                fontSize = 25.sp
            )

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {

                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Gray, CircleShape)
                        .clickable { showProfilePicDialog = true } // Open profile pic dialog
                ) {
                    Image(
                        painter = painterResource(R.drawable.default_profile_pic), // Replace with user's profile pic
                        contentDescription = "User Profile Picture",
                        modifier = Modifier.fillMaxSize()
                                    .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .background(color = Color.Transparent)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Username: ",
                                fontSize = 20.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Email: ",
                                fontSize = 20.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = email,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Password: ",
                                fontSize = 20.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Alert",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Notification",
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                        .padding(horizontal = 10.dp)
                )

                Switch(
                    checked = isNotificationEnabled,
                    onCheckedChange = { isNotificationEnabled = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val termsOfServiceText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(textDecoration = TextDecoration.Underline)
                    ) {
                        append("Terms of Service")
                    }
                }

                Text(
                    text = termsOfServiceText,
                    style = TextStyle(color = Color.Blue),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable { showDialog = true }
                )

                // AlertDialog for Terms of Service
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(text = "Terms of Service") },
                        text = {
                            // Display the Terms of Service text here
                            Text(text = """
                        Welcome to Arctic Vault!
                 
                        By accessing or using the Service, you agree to be bound by these Terms. If you disagree with any part of the terms, then you may not access the Service.
                        1.1. Account: Account: You must create an account to access certain features and are responsible for maintaining its confidentiality and any activities under it.
                        1.2. Use Restrictions: You agree to use the Service provides information only and is not financial advice. Consult a qualified advisor for financial decisions.
                        2.1. Financial Advice: The Service provides information only and is not financial advice. Consult a qualified advisor for financial decisions.
                        3.1. Privacy: Your privacy is important. Review our Privacy Policy for details on data collection and use.
                        4.1. Ownership: All content and features are owned by Arctic Vault or its licensors and are protected by intellectual property laws.
                        4.2. License: Subject to these Terms, we grant you a limited, non-exclusive, non-transferable license to use the Service for your personal, non-commercial use.
                    """.trimIndent())
                        },
                        confirmButton = {
                            Button(
                                onClick = { showDialog = false },
                            ) {
                                Text(text = "OK")
                            }
                        }
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .clickable { showLogoutDialog = true }
                ) {
                    Text(
                        text = "Log Out",
                        color = Color.Red,
                        modifier = Modifier.clickable { showLogoutDialog = true }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Log Out",
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Log Out Confirmation Dialog
                if (showLogoutDialog) {
                    AlertDialog(
                        onDismissRequest = { showLogoutDialog = false },
                        title = { Text(text = "Confirm") },
                        text = { Text(text = "Confirm to log out?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showLogoutDialog = false
                                    // Perform log out action here, such as clearing user data or session
                                    navController.navigate(LoginDestination.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                    }
                                }
                            ) {
                                Text(text = "Yes")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showLogoutDialog = false }
                            ) {
                                Text(text = "No")
                            }
                        }
                    )
                }
            }
        }
    }
}