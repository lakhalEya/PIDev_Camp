package tn.camps.tuncamps.persistence.entity.commun;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseMessage {
    int code;
    String message;
}
