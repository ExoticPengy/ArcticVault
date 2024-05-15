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
import com.example.arcticvault.R

@Composable
fun AccountSetting() {
    var username by remember { mutableStateOf("") }
    val email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsernameEditing by remember { mutableStateOf(false) }
    var isPasswordEditing by remember { mutableStateOf(false) }
    var isNotificationEnabled by remember { mutableStateOf(true) }
    var isCalendarRemindersEnabled by remember { mutableStateOf(true) }
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
                    painter = painterResource(R.drawable.profilepic),
                    contentDescription = "Profile",
                    modifier = Modifier.size(50.dp)
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
                // Profile Picture with Edit Icon
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
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile Picture",
                        tint = Color.White,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.7f), CircleShape)
                            .padding(4.dp)
                            .size(24.dp)
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
                            if (isUsernameEditing) {
                                TextField(
                                    value = username,
                                    onValueChange = { username = it },
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            } else {
                                Text(
                                    text = username.ifEmpty { "Tap to edit" },
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier.clickable { isUsernameEditing = true }
                                )
                            }
                            IconButton(
                                onClick = { isUsernameEditing = !isUsernameEditing },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(Icons.Filled.Edit, contentDescription = "Edit")
                            }
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
                            if (isPasswordEditing) {
                                TextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    modifier = Modifier.padding(start = 8.dp),
                                    visualTransformation = if (password.isEmpty()) PasswordVisualTransformation() else VisualTransformation.None,
                                )
                            } else {
                                Text(
                                    text = if (password.isEmpty()) "Tap to edit" else "******",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier.clickable { isPasswordEditing = true }
                                )
                            }
                            IconButton(
                                onClick = { isPasswordEditing = !isPasswordEditing },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(Icons.Filled.Edit, contentDescription = "Edit")
                            }
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
                )

                Switch(
                    checked = isNotificationEnabled,
                    onCheckedChange = { isNotificationEnabled = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Calendar Reminders",
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = isCalendarRemindersEnabled,
                    onCheckedChange = { isCalendarRemindersEnabled = it },
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

                // AlertDialog
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(text = "Terms of Service") },
                        text = { Text(text = "Here are the terms of service...") },
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
                        .clickable { showConfirmDialog = true }
                ) {
                    Text(
                        text = "Delete Account",
                        color = Color.Red, // Set the color to red
                        modifier = Modifier.clickable { showConfirmDialog = true }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rubbishbin),
                        contentDescription = "Delete Account",
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Delete Account Confirmation Dialog
                if (showConfirmDialog) {
                    AlertDialog(
                        onDismissRequest = { showConfirmDialog = false },
                        title = { Text(text = "Confirm") },
                        text = { Text(text = "Confirm to delete account?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showConfirmDialog = false
                                    // Perform delete account action
                                },
                            ) {
                                Text(text = "Yes")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showConfirmDialog = false },
                            ) {
                                Text(text = "No")
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
                                    // Perform log out action
                                },
                            ) {
                                Text(text = "Yes")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showLogoutDialog = false },
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