package com.resocoder.firemessage.model

import java.util.*

data class TextMessage(val text: String,
//                        val time: Date,
                        val senderId: String,
                        val recipientId: String
)