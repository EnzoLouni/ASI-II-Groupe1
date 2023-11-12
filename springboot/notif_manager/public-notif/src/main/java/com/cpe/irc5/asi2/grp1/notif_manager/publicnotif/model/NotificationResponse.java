package com.cpe.irc5.asi2.grp1.notif_manager.publicnotif.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private boolean operationsWereMade;
    private String message;
    private List<String> errors;
}
