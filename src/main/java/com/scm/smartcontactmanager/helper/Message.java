package com.scm.smartcontactmanager.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Message {
    private String content;
    @Builder.Default
    private MessageType type = MessageType.green;
}
