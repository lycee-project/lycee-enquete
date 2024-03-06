package net.lycee.web.enquete.live;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum QesLiveCommand {
    CONNECT("CONNECT"),
    SEND("SEND"),
    SUBSCRIBE("SUBSCRIBE"),
    UNSUBSCRIBE("UNSUBSCRIBE"),
    DISCONNECT("DISCONNECT")
    ;

    private String command;
    QesLiveCommand(String command) {
        this.command = command;
    }


    public static QesLiveCommand fromValue(String value) {
        return Arrays.stream(values())
                .filter(it -> it.command.equals(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "value is not associated enumeration. [enum=%s, value=%s]"
                                .formatted(QesLiveCommand.class, value)
                ));
    }
}
