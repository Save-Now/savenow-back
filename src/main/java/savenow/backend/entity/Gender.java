package savenow.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * 성별
 */
@AllArgsConstructor
@Getter
public enum Gender {
    MALE("남자"), FEMALE("여자");

    private String value;
}