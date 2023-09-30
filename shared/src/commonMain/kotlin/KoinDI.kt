import org.koin.dsl.module

fun appModule() = module {
    factory { CharactersScreenModel() }
}