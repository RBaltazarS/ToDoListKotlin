package com.example.todolistcrud.Components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.todolistcrud.ui.theme.Black
import com.example.todolistcrud.ui.theme.Purple80
import com.example.todolistcrud.ui.theme.White

@Composable
fun TextBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType,
){
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black,
            focusedBorderColor = Purple80,
            focusedContainerColor = White,
            cursorColor = Purple80,
            focusedLabelColor = Purple80,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}



