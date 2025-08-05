package id.assessment.feature.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UsersPage(modifier: Modifier = Modifier, viewModel: UsersViewModel = hiltViewModel()) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = "",
            onValueChange = { "" },
            label = { Text("Search...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            singleLine = true
        )
    }
}
