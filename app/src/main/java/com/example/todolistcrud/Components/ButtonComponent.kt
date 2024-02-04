package com.example.todolistcrud.Components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todolistcrud.ui.theme.Purple40
import com.example.todolistcrud.ui.theme.White

@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String
){

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Purple40
        )
    )
    {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }

}