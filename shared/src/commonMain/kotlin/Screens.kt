import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class CharactersScreen(): Screen {
    @Composable
    override fun Content() {
        val characterState by getScreenModel<CharactersScreenModel>().stateFlow.collectAsState()
        CharactersList(characterState)
    }

    @Composable
    fun CharactersList(characters: List<Character>) {
        LazyColumn {
            items(characters) {
                CharacterCard(it)
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun CharacterCard(character: Character) {
        val navigator = LocalNavigator.currentOrThrow
        Row (
            Modifier.fillMaxWidth().clickable { navigator.push(CharacterScreen(character)) },
            horizontalArrangement = Arrangement.Start
        )
        {
            Image(
                painter = painterResource(res = character.img),
                contentDescription = character.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier.height(150.dp).width(150.dp)
            )
            Text(character.name)
        }
    }
}

data class CharacterScreen(val character: Character) : Screen {

    @Composable
    override fun Content() {
        CharacterScreen2(character)
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun CharacterScreen2(character: Character) {
        val navigator = LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(res = character.img),
                contentDescription = character.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier.height(150.dp).width(150.dp).padding(16.dp)
            )
            Text(text = character.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = character.description)
            Button(onClick = {navigator.pop()}) {
                Text("Back")
            }
        }
    }
}