package com.eve.manualinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eve.manualinjection.presentation.helper.viewModelFactory
import com.eve.manualinjection.presentation.screen.PostScreen
import com.eve.manualinjection.presentation.screen.PostViewModel
import com.eve.manualinjection.presentation.screen.SplashScreen
import com.eve.manualinjection.ui.theme.ManualInjectionTheme
import com.eve.manualinjection.utils.Utils.showToast
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManualInjectionTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<PostViewModel>(
                        factory = viewModelFactory {
                            PostViewModel(MainApp.appModule.postUseCase)
                        }
                    )
                    val postState by viewModel.postState.collectAsState()
                    val context = LocalContext.current
                    var showSplashScreen by remember { mutableStateOf(true) }

                    if (showSplashScreen) {
                        SplashScreen()
                        LaunchedEffect(key1 = true) {
                            delay(1000)
                            showSplashScreen = false
                        }
                    } else {
                        PostScreen(
                            onClicked = { context.showToast(it) },
                            postState = postState
                        )
                    }
                }
            }
        }
    }
}