package id.assessment.feature.users.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import id.assessment.core.ui.R

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
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.spacing_small),
                            vertical = dimensionResource(
                                id = R.dimen.spacing_xxxs
                            )
                        )
                ) {
                    GlideImage(
                        imageModel = { viewState.user?.avatarUrl },
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
                        Text(text = "Username: ${viewState.user?.login ?: "N/A"}")
                    }
                }
                Text(text = "Bio: ${viewState.user?.bio ?: "N/A"}")
            }
        }
    }
}