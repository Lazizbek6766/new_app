package uz.megasoft.newapp.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uz.megasoft.newapp.R
import uz.megasoft.newapp.ui.theme.Brand

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToWarehouseScreen: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()
    var login by remember { mutableStateOf("") }
    var parol by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val isError by viewModel.isError.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToWarehouseScreen -> {
                    navigateToWarehouseScreen()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(92.dp))
//            Icon(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "logo",
//                tint = Brand
//            )
            Spacer(modifier = Modifier.height(105.dp))

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = {
                    viewModel.register(
                        email = "legendar3@gmail.com",
                        password = "123456789",
                        "Lazizbek"
                    )
                }
            ){
                Text(text = "Kirish")
            }
        }
    }
    if (isError){

    }
}

@Preview(showBackground = true)
@Composable
fun PreLoginScreen() {

}