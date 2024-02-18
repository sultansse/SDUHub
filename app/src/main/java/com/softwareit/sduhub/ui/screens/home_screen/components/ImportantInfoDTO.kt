package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.common.utils.empty
import com.softwareit.sduhub.data.network.FirebaseRealtimeDatabaseWrapper


@Composable
fun ImportantInfo(data: ImportantInfoDTO) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .border(
                width = 2.dp,
                color = Color.Red,
                shape = RoundedCornerShape(10),
            )
    ) {
        Column {
            Text(
                text = data.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = data.description
            )
        }
    }
}

data class ImportantInfoDTO(
    val title: String,
    val description: String,
    val tags: List<String> = emptyList(),
) {
    constructor() : this(String.empty, String.empty)
}

class ImportantInfoDao : FirebaseRealtimeDatabaseWrapper<ImportantInfoDTO>() {
    override fun getTableName(): String = "important_info_table"

    override fun getClassType(): Class<ImportantInfoDTO> = ImportantInfoDTO::class.java
}