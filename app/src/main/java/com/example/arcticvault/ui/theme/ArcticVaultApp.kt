package com.example.arcticvault.ui.theme

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arcticvault.Data.Debt
import com.example.arcticvault.EditTransaction
import com.example.arcticvault.EditTransactionDestination
import com.example.arcticvault.Transactions
import com.example.arcticvault.TransactionsDestination
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun ArcticVaultApp(){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController, //StartPageDestination.route
        startDestination = AccountSettingDestination.route//DebtDetailsDestination.route
    ){
        composable(route = StartPageDestination.route) {
            LoginRegisterScreen(navController)
        }
        composable(route = LoginDestination.route) {
            LoginScreen(navController)
        }
        composable(route = SignupDestination.route) {
            SignUp(navController)
        }

        composable(route = AccountSettingDestination.route) {
            AccountSetting(navController)
        }

        composable(route = "debtDetail/{debtJson}") { backStackEntry ->
            val debtJson = backStackEntry.arguments?.getString("debtJson")
            val decodedDebtJson = debtJson?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            val debt = decodedDebtJson?.let { Gson().fromJson(it, Debt::class.java) }
            if (debt != null) {
                Log.d("Navigation", "I can go debt details")
                DebtDetails(navController, debt)
            } else{
                Log.d("Navigation", "I cannot go debt details")
            }
        }
        composable(route = DebtDestination.route){
            Debt(navController = navController)
        }


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
                onTransactionsButton = {},
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