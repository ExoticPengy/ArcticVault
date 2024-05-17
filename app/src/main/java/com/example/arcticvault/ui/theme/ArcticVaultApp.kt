package com.example.arcticvault.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arcticvault.EditTransaction
import com.example.arcticvault.EditTransactionDestination
import com.example.arcticvault.Transactions
import com.example.arcticvault.TransactionsDestination


@Composable
fun ArcticVaultApp(){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BudgetingDestination.route
    ){
        composable(route = BudgetingDestination.route){
            Budgeting(
                onPreviousButton = { navController.navigateUp() },
                onBudgetingInputButton = { navController.navigate(BudgetingInputDestination.route) }
            )
        }
        composable(route = BudgetingInputDestination.route) {
            BudgetingInput(
                onPreviousButton = { navController.navigateUp() },
                onCancelButton = { navController.navigateUp() }
                )
        }
        composable(route = FinancialGoalsDestination.route) {
            Finance(
                onGoalClick = { navController.navigate("${EditGoalsDestination.route}/$it") },
                onEditGoalsButton = { navController.navigate(EditGoalsInputDestination.route) },
                onPreviousButton = { navController.navigateUp() },
            )
        }
        composable(route = EditGoalsDestination.route ){
            Edit_Goals(
                onTransactionsButton = {navController.navigate(AllTransactionsDestination.route)},
                onEditGoalsButton = {navController.navigate(EditGoalsInputDestination.route)},
                onPreviousButton = { navController.navigateUp() })
        }
        composable(route = EditGoalsInputDestination.route) {
            EditGoalsInput(
                onPreviousButton = { navController.navigateUp() },
                onCancelButton = { navController.navigateUp() },
            )
        }
        composable(
            route = EditGoalsDestination.routeWithArgs,
            arguments = listOf(navArgument(EditGoalsDestination.goalIdArg) {
                type = NavType.IntType
            })
        ) {
            Edit_Goals(
                onTransactionsButton = {navController.navigate(AllTransactionsDestination.route)},
                onEditGoalsButton = {navController.navigate("${EditGoalsInputDestination.route}/$it") },
                onPreviousButton = { navController.navigateUp() })
        }
        composable(
            route = EditGoalsInputDestination.routeWithArgs,
            arguments = listOf(navArgument(EditGoalsInputDestination.goalIdArg) {
                type = NavType.IntType
            })
        ) {
            EditGoalsInput(
                onPreviousButton = { navController.navigateUp() },
                onCancelButton = { navController.navigateUp() },
            )
        }
        composable(
            route = BudgetingInputDestination.routeWithArgs,
            arguments = listOf(navArgument(BudgetingInputDestination.budgetIdArg) {
                type = NavType.IntType
            })
        ) {
            BudgetingInput(
                onPreviousButton = { navController.navigateUp() },
                onCancelButton = { navController.navigateUp() }
            )
        }
        composable(
            route = BudgetingInputDestination.routeWithArgs,
            arguments = listOf(navArgument(BudgetingInputDestination.budgetIdArg) {
                type = NavType.IntType
            })
        ) {
            Budgeting(
                onPreviousButton = { navController.navigateUp() },
                onBudgetingInputButton = { navController.navigate("${BudgetingInputDestination.route}/$it") }
            )
        }
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