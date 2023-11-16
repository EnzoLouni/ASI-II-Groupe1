package com.cpe.irc5.asi2.grp1.store_manager.dto;

import com.cpe.irc5.asi2.grp1.commons.model.DataBusObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreOrder extends DataBusObject {
    private Integer cardId;
    private Integer userId;
}
