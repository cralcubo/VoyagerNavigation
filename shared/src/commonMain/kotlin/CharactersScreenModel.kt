import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow

class CharactersScreenModel : ScreenModel {
    private val mutableStateFlow = MutableStateFlow(emptyList<Character>())
    val stateFlow by ::mutableStateFlow

    init {
        mutableStateFlow.value = characters()
    }


}