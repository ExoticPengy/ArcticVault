package com.example.arcticvault.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcticvault.R

@Composable
fun DebtDetails() {
    var remarkText by remember { mutableStateOf("") }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Surface {
        Image(
            painter = painterResource(R.drawable.topbanner),
            contentDescription = "Top Banner",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.size(30.dp)
                        .clickable { /*navController.navigate("home")*/ }
                )
                Text(
                    text = "Debt Details",
                    fontSize = 25.sp,
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
                text = "Manage all your debts in Arctic Vault",
                modifier = Modifier.padding(top = 3.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Loan Descriptions",
                modifier = Modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold // Set fontWeight to Bold
            )
            Text(
                text = "Category *",
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Name *",
                modifier = Modifier.padding(top = 10.dp)
            )

            Divider(color = Color.Gray, thickness = 1.5.dp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 15.dp))

            Text(
                text = "Terms",
                modifier = Modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold // Set fontWeight to Bold
            )
            Text(
                text = "Current Balance *",
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Annual Percentage Rate *",
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Payment Details",
                modifier = Modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold // Set fontWeight to Bold
            )
            Text(
                text = "Payment frequency *",
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Annual Percentage Rate *",
                modifier = Modifier.padding(top = 10.dp)
            )

            Divider(color = Color.Gray, thickness = 1.5.dp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 15.dp))

            Text(
                text = "Minimum Payment *",
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Remark *",
                modifier = Modifier.padding(top = 10.dp)
            )
            // Text field with placeholder
            TextField(
                value = remarkText,
                onValueChange = { remarkText = it },
                placeholder = {
                    Text(
                        text = "Remark",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                textStyle = TextStyle(color = Color.Black)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { showConfirmDialog = true }
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Delete",
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
                                /* Perform delete account action */
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
        }
    }
}