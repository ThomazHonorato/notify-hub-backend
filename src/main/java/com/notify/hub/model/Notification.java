package com.notify.hub.model;

import com.notify.hub.enums.NotificationStatus;
import com.notify.hub.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name = "Notification")
@Entity(name = "Notification")
public class Notification {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

@Column(nullable = false)
private Instant createdAt;

@Column
private Instant updatedAt;

@Column
private Instant deletedAt;

@Column(nullable = false)
private String title;

@Column(nullable = false)
private String message;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private NotificationType type;

@Column(nullable = false)
private UUID userId;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private NotificationStatus status;

}
