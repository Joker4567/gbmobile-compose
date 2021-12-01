package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.enaza.gb.gbmobile_theme.theme.*

@Composable
fun SearchBar(
        query: String,
        onQueryChange: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    GbSurface (
            color = uiFloated,
            contentColor = textSecondary,
            shape = RoundedCornerShape(25.dp),
            modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.isEmpty()) {
                SearchHint()
            }
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight()
            ) {
                BasicTextField(
                        value = query,
                        onValueChange = onQueryChange,
                        modifier = Modifier
                                .weight(1f)
                                .padding(start = 20.dp, end = 20.dp),
                        singleLine = true
                )
                if(query.isNotBlank()) {
                    Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black,
                            modifier = Modifier
                                    .padding(end = 15.dp)
                                    .clickable { onQueryChange.invoke("") }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchHint() {
    Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
    ) {
        Icon(
                imageVector = Icons.Outlined.Search,
                tint = Black700,
                contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
                text = "Введите название игры или жанра",
                color = Black700
        )
    }
}