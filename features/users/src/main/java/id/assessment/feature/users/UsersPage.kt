package id.assessment.feature.users

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.assessment.core.ui.R as RC

@Composable
fun UsersPage(modifier: Modifier = Modifier, viewModel: UsersViewModel = hiltViewModel()) {
    val viewState by viewModel.viewState.collectAsState()

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = RC.dimen.spacing_medium)),
            shape = RoundedCornerShape(dimensionResource(id = RC.dimen.corner_large)),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        if (viewState.searchQuery.isNotEmpty()) {
                            viewModel.onSearchUsers("")
                        }
                    },
                    imageVector = if (viewState.searchQuery.isEmpty()) Icons.Rounded.Search else Icons.Rounded.Clear,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.users_hint_search),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            value = viewState.searchQuery,
            textStyle = MaterialTheme.typography.bodyLarge,
            onValueChange = { query ->
                viewModel.onSearchUsers(query)
            }
        )

        when {
            viewState.loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            viewState.users.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Search Users")
                }
            }

            else -> {
                LazyColumn(
                    modifier = modifier,
                ) {
                    items(viewState.users) { user ->
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "ID: ${user.id}")
                            Text(text = "Login: ${user.login ?: "N/A"}")
                            Text(text = "Avatar: ${user.avatarUrl ?: "N/A"}")
                            Text(text = "Html: ${user.htmlUrl ?: "N/A"}")
                        }
                    }
                }
            }
        }
    }
}
