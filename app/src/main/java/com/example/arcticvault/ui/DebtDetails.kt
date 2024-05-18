package com.example.arcticvault.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.arcticvault.R
import com.example.arcticvault.data.Debt
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DebtDetailsDestination {
    val route = "debtDetail"
}

@Composable
fun DebtDetails(navController: NavController, debt: Debt, debtEntryViewModel: DebtEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    var showConfirmDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
                        .clickable { navController.navigateUp() }
                )
                Text(
                    text = "Debt Details",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .width(IntrinsicSize.Min)
                )
               Image(
                   painter = painterResource(R.drawable.default_profile_pic),
                   contentDescription = "Profile",
                    modifier = Modifier.size(50.dp)
                        .clip(CircleShape)
                        .clickable { /*navController.navigate("AccountSetting") */}
               )
            }
            Text(
                text = "Manage all your debts in",
                modifier = Modifier.padding(top = 1.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Arctic Vault",
                modifier = Modifier.padding(top = 1.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Loan Descriptions",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Category: ${debt.categories}",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = "Name: ${debt.nickname}",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )

            Divider(color = Color.Gray, thickness = 1.5.dp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 15.dp))

            Text(
                text = "Terms",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Current Balance: ${debt.currentBalance} ",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = "Annual Percentage Rate: ${debt.annualRate}%",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )
            Divider(color = Color.Gray, thickness = 1.5.dp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 15.dp))
            Text(
                text = "Payment Details",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Payment frequency: ${debt.paymentFrequency}",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )

            Text(
                text = "Minimum Payment: ${debt.minPayment}",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )


            Divider(color = Color.Gray, thickness = 1.5.dp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 15.dp))

            val originalDate = LocalDate.parse(debt.date, DateTimeFormatter.ofPattern("d/M/yyyy"))
            val updatedDate = originalDate.plusMonths(1)
            val formattedUpdatedDate = updatedDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"))
            Text(
                text = "Debt Payoff Date: ${formattedUpdatedDate}",
                modifier = Modifier.padding(top = 10.dp)
                    .padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { showConfirmDialog = true }
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Delete",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add some space between the text and the image
                Image(
                    painter = painterResource(id = R.drawable.rubbishbin),
                    contentDescription = "Delete Debt",
                    modifier = Modifier.size(24.dp)
                )
            }

            // Delete Account Confirmation Dialog
            if (showConfirmDialog) {
                AlertDialog(
                    onDismissRequest = { showConfirmDialog = false },
                    title = { Text(text = "Confirm") },
                    text = { Text(text = "Confirm to delete debt?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                showConfirmDialog = false
                                /* Perform delete account action */
//                                coroutineScope.launch {
//                                    debtEntryViewModel.
//                                }
                                //Perform delete action
                                coroutineScope.launch {
                                    debtEntryViewModel.deleteDebt(debt)
                                }
                                navController.navigateUp()
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