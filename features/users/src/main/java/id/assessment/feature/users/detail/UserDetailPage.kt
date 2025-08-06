package id.assessment.feature.users.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import id.assessment.data.users.model.User
import id.assessment.feature.users.R
import id.assessment.core.ui.R as RC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailPage(
    userId: Int?,
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(userId) {
        if (userId != null) {
            viewModel.getUserDetail(userId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.users_user_detail_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        when {
            viewState.loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            viewState.user != null -> {
                DetailUser(user = viewState.user, Modifier.padding(it))
            }
        }
    }
}

@Composable
fun DetailUser(user: User?, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = dimensionResource(id = RC.dimen.spacing_small))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = RC.dimen.spacing_small),
                    vertical = dimensionResource(
                        id = RC.dimen.spacing_xxxs
                    )
                ),
            verticalAlignment = Alignment.Top
        ) {
            GlideImage(
                imageModel = { user?.avatarUrl },
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                loading = {
                    // optional: add loading placeholder here
                },
                failure = {
                    // optional: add error placeholder here
                }
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "${user?.name ?: "N/A"}", style = MaterialTheme.typography.titleMedium)
                Text(text = "${user?.login ?: "N/A"}")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = RC.dimen.spacing_xxxs)))
                Text(text = "${user?.followers ?: "N/A"} Follower")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = RC.dimen.spacing_xxxs)))
                Text(text = "${user?.following ?: "N/A"} Following")
            }
        }
        Text(text = "Bio:")
        Text(text = "${user?.bio ?: "N/A"}")
    }
}