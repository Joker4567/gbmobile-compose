package pro.enaza.gb.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun FullScreenDialog(showDialog:Boolean, onClose:()->Unit) {
    if (showDialog) {
        Dialog(onDismissRequest =  onClose) {
            Surface(
                    modifier = Modifier.width(150.dp).height(150.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.LightGray
            ) {
                Box(
                        contentAlignment = Alignment.Center
                ) {
                    Text(modifier = Modifier.align(Alignment.TopCenter), text = "top")
                    Text("center")
                    Text(modifier = Modifier.align(Alignment.BottomCenter), text = "bottom")
                }
            }
        }
    }
}