package com.example.arcticvault

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class ArcticVault() {
    Start,
    All,
    Add,
    Edit
}

@Composable
fun ArcticVaultApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ArcticVault.Start.name
    ) {
        composable(route = ArcticVault.Start.name) {
            AllTransactions(onTransactionClick = {  navController.navigate("${EditTransactionDestination.route}/$it") } )
        }
        composable(route = ArcticVault.Edit.name) {
            EditTransaction(onBackButtonClick = { navController.navigateUp() }, onConfirmButtonClick = { navController.navigateUp() })
        }
        composable(
            route = EditTransactionDestination.routeWithArgs,
            arguments = listOf(navArgument(EditTransactionDestination.transactionIdArg) {
                type = NavType.IntType
            })
        ) {
            EditTransaction(
                onBackButtonClick = { navController.navigateUp() },
                onConfirmButtonClick = { navController.navigateUp() }
            )
        }
    }
}