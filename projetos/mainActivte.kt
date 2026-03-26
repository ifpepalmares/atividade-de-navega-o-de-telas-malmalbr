package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b?message={message}") {
            val message = it.arguments?.getString("message")
            ScreenB(navController = navController, message = message)
        }
        composable("screen_c"){
            ScreenC(navController = navController)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.robo),
            contentDescription = "Logo Android Interpraise",
            modifier = Modifier
                .size(160.dp)
                .padding(bottom = 16.dp)
        )

        Text(text = "Bem-vindo a Android Interpraise!")
        Button(
            onClick = { navController.navigate("screen_b?message=no momento atual não temos nada para oferecer, pesso minhas mais sinceras desculpas!") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.black),
                contentColor = colorResource(R.color.white)
            ) ) {
            Text("Ver nossos produtos")
        }
        Button(
            onClick = { navController.navigate("screen_b?message=Email: suporte@android.com") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.black),
                contentColor = colorResource(R.color.white)
            ) ) {
            Text("Informações para contato")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "No momento atual não temos nada para oferecer, pesso minhas mais sinceras desculpas!",
            style = MaterialTheme.typography.headlineMedium
        )
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Voltar")
        }
    }
}
@Composable
fun ScreenC(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Contato",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(text = "Email: suporte@android.com")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    ComposeNavigationAppTheme {
        ScreenA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    ComposeNavigationAppTheme {
        ScreenB(rememberNavController(), "Olá da Tela A")
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCPreview() {
    ComposeNavigationAppTheme {
        ScreenC(rememberNavController())
    }
}
