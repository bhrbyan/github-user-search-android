package id.assessment.feature.users.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UserDetailPage(
    userId: Int?,
    navController: NavController
) {
    Column {
        Text(text = "User Id : $userId")
    }
}