package effective.office.marvelproject.presentation.model

data class CharacterUI(
    val id: Int,
    val logo: String,
    val name: String,
    val description: String
) {
    companion object {
        val Empty = CharacterUI(
            0,
            "",
            "",
            ""
        )
    }
}
