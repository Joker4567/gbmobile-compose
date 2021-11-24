package pro.enaza.gb.feature_search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_ui.component.CatalogCard

@Composable
internal fun SearchContent(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        login: String,
        onLoginChange: (String) -> Unit,
        onClear: (Boolean) -> Unit,
        gameCardList: List<GameCard>,
        onCardClick: (GameCard) -> Unit
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val onNext = remember { { passwordFocusRequester.requestFocus() } }
    val onClearEmail = remember { onClear }

    Scaffold(scaffoldState = scaffoldState) {
        Box(Modifier.fillMaxSize()) {
            Column(
                    Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                        text = "Поиск по каталогу".uppercase(),
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 25.dp, bottom = 25.dp)
                )
                SearchField(
                        value = login,
                        onValueChange = onLoginChange,
                        onNext = onNext,
                        onClear = onClearEmail
                )
                CatalogCard(
                        onCardClick = onCardClick,
                        cardList = gameCardList,
                        modifier = Modifier.padding(top = 25.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
internal fun SearchField(
        value: String,
        onValueChange: (String) -> Unit,
        onNext: () -> Unit,
        onClear: (Boolean) -> Unit
) {
    TextField(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
            shape = RoundedCornerShape(25.dp),
            value = value,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(textAlign = TextAlign.Center, color = Color.LightGray, text = "Введите название игры или жанра") },
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { onNext() }),
            trailingIcon = {
                if (value.isNotBlank())
                    Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black,
                            modifier = Modifier.clickable { onClear.invoke(true) }
                    )
            }
    )
}