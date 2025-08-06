package id.assessment.githubusersearch

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.assessment.feature.users.detail.UserDetailPage
import id.assessment.feature.users.list.UsersPage
import id.assessment.feature.users.navigation.UsersNavigationRoute
import id.assessment.feature.users.navigation.userNavigation

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = UsersNavigationRoute.UsersPage.route) {
        userNavigation(navController)
    }
}
