package id.assessment.feature.users.navigation

sealed class UsersNavigationRoute(val route: String) {

    data object UsersPage : UsersNavigationRoute("users_page")
    data object UserDetailPage : UsersNavigationRoute("users_detail_page")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}