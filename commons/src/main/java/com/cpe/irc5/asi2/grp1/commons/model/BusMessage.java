package com.cpe.irc5.asi2.grp1.commons.model;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusMessage {
    private GroupID groupID;
    private RequestType requestType;
    private RequestOrigin origin;
    private String socketId;
    private DataBusObject dataBusObject;
    private Class classOfDataBusObject;
}
