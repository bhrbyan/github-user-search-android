package id.assessment.feature.users.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.assessment.feature.users.detail.UserDetailPage
import id.assessment.feature.users.list.UsersPage


fun NavGraphBuilder.userNavigation(navController: NavHostController) {
    composable(UsersNavigationRoute.UsersPage.route) {
        UsersPage(navController)
    }
    composable(
        route = UsersNavigationRoute.UserDetailPage.route + "/{userId}",
        arguments = listOf(
            navArgument("userId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )) {
        val userId = it.arguments?.getInt("userId")
        UserDetailPage(userId = userId, navController = navController)
    }
}
