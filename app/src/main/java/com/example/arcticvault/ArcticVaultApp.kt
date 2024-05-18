package com.example.arcticvault

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arcticvault.data.Debt
import com.example.arcticvault.ui.AccountSetting
import com.example.arcticvault.ui.AccountSettingDestination
import com.example.arcticvault.ui.AllTransactions
import com.example.arcticvault.ui.AllTransactionsDestination
import com.example.arcticvault.ui.Budgeting
import com.example.arcticvault.ui.BudgetingDestination
import com.example.arcticvault.ui.BudgetingInput
import com.example.arcticvault.ui.BudgetingInputDestination
import com.example.arcticvault.ui.DebtDestination
import com.example.arcticvault.ui.DebtDetails
import com.example.arcticvault.ui.DebtScreen
import com.example.arcticvault.ui.EditGoalsDestination
import com.example.arcticvault.ui.EditGoalsInput
import com.example.arcticvault.ui.EditGoalsInputDestination
import com.example.arcticvault.ui.EditTransaction
import com.example.arcticvault.ui.EditTransactionDestination
import com.example.arcticvault.ui.Edit_Goals
import com.example.arcticvault.ui.Finance
import com.example.arcticvault.ui.FinancialGoalsDestination
import com.example.arcticvault.ui.HomeDestination
import com.example.arcticvault.ui.HomeScreen
import com.example.arcticvault.ui.LoginDestination
import com.example.arcticvault.ui.LoginRegisterScreen
import com.example.arcticvault.ui.LoginScreen
import com.example.arcticvault.ui.ReminderScreen
import com.example.arcticvault.ui.SignUp
import com.example.arcticvault.ui.SignupDestination
import com.example.arcticvault.ui.StartPageDestination
import com.example.arcticvault.ui.Transactions
import com.example.arcticvault.ui.TransactionsAnalysis
import com.example.arcticvault.ui.TransactionsAnalysisDestination
import com.example.arcticvault.ui.TransactionsDestination
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun ArcticVaultApp() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StartPageDestination.route
    ) {
        /* ============================ACCOUNT NAVIGATION============================ */
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

        /* ============================DEBT NAVIGATION============================ */

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
            DebtScreen(navController = navController)
        }

        /* ============================HOME NAVIGATION============================ */
        composable(route = HomeDestination.route) {
            HomeScreen(
                onTransactionClick = { navController.navigate(TransactionsDestination.route) },
                onBudgetClick = { navController.navigate(BudgetingDestination.route) },
                onAnalysisClick = { navController.navigate(TransactionsAnalysisDestination.route) },
                onGoalClick = { navController.navigate(FinancialGoalsDestination.route) },
                onReminderClick = {navController.navigate("reminder") },
                onDebtClick = { navController.navigate(DebtDestination.route) }
            )
        }

        /* ============================REMINDER NAVIGATION============================ */
        composable("reminder") {
            ReminderScreen(onBackButtonClick = { navController.navigateUp() } )
        }

        /* ============================TRANSACTIONS NAVIGATION============================ */
        composable(route = TransactionsDestination.route) {
            Transactions(
                onAddExpenseClick = { navController.navigate("${EditTransactionDestination.route}/Expense") },
                onAddIncomeClick = { navController.navigate("${EditTransactionDestination.route}/Income") },
                onViewAllClick = { navController.navigate(AllTransactionsDestination.route) },
                onBackButtonClick = { navController.navigateUp() }
            )
        }
        composable(route = AllTransactionsDestination.route) {
            AllTransactions(
                onTransactionClick = { navController.navigate("${EditTransactionDestination.route}/$it") },
                onBackButtonClick = { navController.navigateUp() }
            )
        }
        composable(route = "${EditTransactionDestination.route}/Expense") {
            EditTransaction(
                addNewExpense = true,
                onButtonClick = { navController.navigateUp() })
        }
        composable(route = "${EditTransactionDestination.route}/Income") {
            EditTransaction(
                addNewIncome = true,
                onButtonClick = { navController.navigateUp() })
        }
        composable(
            route = EditTransactionDestination.routeWithArgs,
            arguments = listOf(navArgument(EditTransactionDestination.transactionIdArg) {
                type = NavType.IntType
            })
        ) {
            EditTransaction(onButtonClick = { navController.navigateUp() })
        }
        composable(route = TransactionsAnalysisDestination.route) {
            TransactionsAnalysis(onButtonClick = { navController.navigateUp() })
        }
        /* ============================BUDGETING NAVIGATION============================ */
        composable(route = BudgetingDestination.route) {
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
        /* ============================FINANCIAL GOALS NAVIGATION============================ */
        composable(route = FinancialGoalsDestination.route) {
            Finance(
                onGoalClick = { navController.navigate("${EditGoalsDestination.route}/$it") },
                onEditGoalsButton = { navController.navigate(EditGoalsInputDestination.route) },
                onPreviousButton = { navController.navigateUp() },
            )
        }
        composable(route = EditGoalsDestination.route) {
            Edit_Goals(
                onTransactionsButton = { navController.navigate(AllTransactionsDestination.route) },
                onEditGoalsButton = { navController.navigate(EditGoalsInputDestination.route) },
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
                onTransactionsButton = { navController.navigate(AllTransactionsDestination.route) },
                onEditGoalsButton = { navController.navigate("${EditGoalsInputDestination.route}/$it") },
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
    }
}