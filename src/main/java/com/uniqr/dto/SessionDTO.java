package com.uniqr.dto;

import com.uniqr.model.QR;
import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private String id;
    private String name;
    private Long amount;
    private Date created;
    private String desc;
    private String imageURL;
    private String clientDomain;
    private List<String> qrs;

    public static Session mapToSession(SessionDTO sessionDTO) {
        Date date = new Date();

        return new Session(
                sessionDTO.getName(),
                sessionDTO.getAmount(),
                date,
                sessionDTO.getDesc()
        );
    }

    public static SessionDTO mapToSessionDTO(Session session) {
        List<String> collect = session.getQrs().stream().map(QR::getId).toList();
        String imageURL = null;
        if(session.getImage() != null) {
            imageURL = "https://pegazzo.online:8081/api/v1/images/" + session.getImage().getId();
        }

        return new SessionDTO(
                session.getId(),
                session.getName(),
                session.getAmountQRs(),
                session.getCreated(),
                session.getDesc(),
                imageURL,
                session.getClientDomain(),
                collect
        );
    }
}
