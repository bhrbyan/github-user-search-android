package id.assessment.feature.users.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import id.assessment.data.users.model.User
import id.assessment.feature.users.R
import id.assessment.feature.users.navigation.UsersNavigationRoute
import id.assessment.core.ui.R as RC

@Composable
fun UsersPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {
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
                            viewModel.clearSearch()
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
                viewModel.onQueryChange(query)
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
                    Text(text = stringResource(id = R.string.users_empty))
                }
            }

            else -> {
                ListUsers(users = viewState.users, onClick = { userId ->
                    navController.navigate(route = UsersNavigationRoute.UserDetailPage.withArgs("$userId"))
                })
            }
        }
    }
}

@Composable
fun ListUsers(users: List<User>, modifier: Modifier = Modifier, onClick: (userId: Int) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = RC.dimen.spacing_small)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onClick(user.userId) }
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                GlideImage(
                    imageModel = { user.avatarUrl },
                    modifier = Modifier
                        .size(80.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = user.login,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListUsers() {
    ListUsers(
        onClick = {},
        users = listOf(
            User(null,
                "JakeWharton",
                3392020,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            ),
            User(null,
                "Jaker",
                232523,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            ),
            User(null,
                "JakkyChan",
                2341454,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            ),
        )
    )
}
