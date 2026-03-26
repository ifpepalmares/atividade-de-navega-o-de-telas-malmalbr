package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme

// Classe principal do aplicativo.
// É ela que inicia tudo quando o app é aberto.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent define o conteúdo visual do app usando Jetpack Compose.
        setContent {
            ComposeNavigationAppTheme {

                // Surface funciona como uma "camada base" da tela.
                // Aqui eu defini que ela vai ocupar a tela inteira
                // e usei uma cor de fundo personalizada.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.background_app)
                ) {
                    // Chama a função que controla a navegação entre telas.
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    // Cria o controlador de navegação.
    // Ele é responsável por trocar de uma tela para outra.
    val navController = rememberNavController()

    // NavHost é onde definimos todas as rotas do app.
    // A tela inicial será "screen_a".
    NavHost(navController = navController, startDestination = "screen_a") {

        // Rota da primeira tela.
        composable("screen_a") {
            ScreenA(navController = navController)
        }

        // Rota da segunda tela.
        // Ela recebe uma mensagem como parâmetro.
        composable("screen_b/{message}") {
            ScreenB(navController = navController)
        }

        // Rota da terceira tela.
        composable("screen_c") {
            ScreenC(navController = navController)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {

    // Column organiza os elementos um embaixo do outro.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        // Centraliza os elementos na vertical.
        verticalArrangement = Arrangement.Center,

        // Centraliza os elementos na horizontal.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Imagem do robô.
        Image(
            painter = painterResource(R.drawable.robo),
            contentDescription = "Logo Android Interpraise",
            modifier = Modifier
                .size(160.dp)
                .padding(bottom = 16.dp)
        )

        // Texto principal da tela inicial.
        Text(
            text = "Bem-vindo a Android Interpraise!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(R.color.text_primary)
        )

        // Espaço entre o título e os botões.
        Spacer(modifier = Modifier.height(24.dp))

        // Botão para ir para a tela de produtos.
        Button(
            onClick = {
                navController.navigate("screen_b/No momento atual não temos nada para oferecer, peço minhas mais sinceras desculpas!")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_primary),
                contentColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(12.dp),// deixa os cantos do botão arredondados
            modifier = Modifier.padding(vertical = 8.dp)// adiciona espaço em cima e embaixo do botão
        ) {
            Text(
                text = "Ver nossos produtos",
                textAlign = TextAlign.Center
            )
        }

        // Botão para ir para a tela de contato.
        Button(
            onClick = {
                navController.navigate("screen_c")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_secondary),
                contentColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "Informações para contato",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ScreenB(navController: NavController) {

    // Tela que mostra a mensagem recebida da ScreenA.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        // Agora centraliza na vertical.
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título da tela.
        Text(
            text = "Produtos:",
            style = MaterialTheme.typography.headlineMedium,
            color = colorResource(R.color.text_primary),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

       // texto segundario para informar que não temos produtos
        Text(
            text = "No momento atual não temos nada para oferecer, peço minhas mais sinceras desculpas!",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.text_secondary),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão para voltar para a tela anterior.
        Button(
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_primary),
                contentColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Voltar")
        }
    }
}

@Composable
fun ScreenC(navController: NavController) {

    // Tela de contato.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título da tela de contato.
        Text(
            text = "Contato:",
            style = MaterialTheme.typography.headlineMedium,
            color = colorResource(R.color.text_primary),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Informações de contato.
        Text(
            text = "Email: suporte@android.com",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.text_secondary),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Telefone: 55+ (81) 9 9465-2453",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.text_secondary),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão de voltar.
        Button(
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_secondary),
                contentColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Voltar")
        }
    }
}

// Preview da tela A para visualizar no Android Studio.
@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    ComposeNavigationAppTheme {
        ScreenA(rememberNavController())
    }
}

// Preview da tela B.
@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    ComposeNavigationAppTheme {
        ScreenB(rememberNavController())
    }
}

// Preview da tela C.
@Preview(showBackground = true)
@Composable
fun ScreenCPreview() {
    ComposeNavigationAppTheme {
        ScreenC(rememberNavController())
    }
}
