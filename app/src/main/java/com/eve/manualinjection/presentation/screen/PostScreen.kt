package com.eve.manualinjection.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eve.manualinjection.data.local.PostEntity
import com.eve.manualinjection.presentation.screen.component.PostItem
import com.eve.manualinjection.presentation.screen.component.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    onClicked: (String) -> Unit,
    postState: UiState<List<PostEntity>>,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Post List")
                }
            )
        }
    ) { innerPadding ->
        when (postState) {
            is UiState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val postList = postState.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(postList) { item ->
                        PostItem(
                            postEntity = item,
                            onClicked = {
                                onClicked(it)
                            }
                        )
                    }
                }
            }
            is UiState.Failure -> Text(
                text = "Error ${postState.message}",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}