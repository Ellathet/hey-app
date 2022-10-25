package br.com.hey.model

data class NotificationResponse (
    val id: String,
    val title: String,
    val text: String,
    val sentAt: String,
    val createdAt: String
)

class NotificationsResponse : ArrayList<NotificationResponse>()