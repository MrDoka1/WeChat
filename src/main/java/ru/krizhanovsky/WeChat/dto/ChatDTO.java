package ru.krizhanovsky.WeChat.dto;

import jakarta.validation.constraints.Positive;
import ru.krizhanovsky.WeChat.classes.ChatOutput;

public enum ChatDTO {;

    private interface Id { @Positive Long getId(); }
    private interface IsDialog { boolean getIsDialog(); };
}
