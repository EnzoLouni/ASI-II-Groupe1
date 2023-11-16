package com.cpe.irc5.asi2.grp1.notif_manager.model;

import com.cpe.irc5.asi2.grp1.commons.model.DataBusObject;
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
public class NotificationResponse extends DataBusObject {
    private boolean operationsWereMade;
    private String message;
    private List<String> errors;
}
