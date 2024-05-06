package com.example.arcticvault

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ArcticVaultApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TransactionsDestination.route
    ) {
        composable(route = TransactionsDestination.route) {
            Transactions(
                onAddExpenseClick = { navController.navigate("${EditTransactionDestination.route}/Expense") },
                onAddIncomeClick = { navController.navigate("${EditTransactionDestination.route}/Income") },
                onViewAllClick = { navController.navigate(AllTransactionsDestination.route) }
            )
        }
        composable(route = AllTransactionsDestination.route) {
            AllTransactions(
                onTransactionClick = {  navController.navigate("${EditTransactionDestination.route}/$it") },
                onBackButtonClick = { navController.navigateUp() }
            )
        }
        composable(route = "${EditTransactionDestination.route}/Expense") {
            EditTransaction(addNewExpense = true, onButtonClick = { navController.navigateUp() })
        }
        composable(route = "${EditTransactionDestination.route}/Income") {
            EditTransaction(addNewIncome = true, onButtonClick = { navController.navigateUp() })
        }
        composable(
            route = EditTransactionDestination.routeWithArgs,
            arguments = listOf(navArgument(EditTransactionDestination.transactionIdArg) {
                type = NavType.IntType
            })
        ) {
            EditTransaction(onButtonClick = { navController.navigateUp() })
        }
    }
}